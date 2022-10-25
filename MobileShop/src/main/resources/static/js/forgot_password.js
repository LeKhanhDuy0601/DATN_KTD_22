const app = angular.module("forgot-password-app", []);
app.controller("forgot-password-ctrl", function($scope, $http){
	
	$scope.form = {};
	$scope.error = null;
	$scope.verification = null;
	
	$scope.send_verification = function(){
		var account = angular.copy($scope.form);
		$scope.error = null;
		$scope.form = {};
		$http.post(`/rest/tokens`, account).then(resp => {
			$scope.verification = 1;
			$scope.form.account = resp.data.account;
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: resp.data.message,
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			$scope.error = error.data;
			if($scope.error.type == 'username'){
				$scope.error.username = $scope.error.message;
			}
			if($scope.error.type == 'email'){
				$scope.error.email = $scope.error.message;
			}
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		})
	}
	
	$scope.forgot_password = function(){
		var account = angular.copy($scope.form);
		account.type = 1;
		$scope.error = null;
		$http.post(`/rest/accounts/forgot-password`, account).then(resp => {
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: resp.data.message,
				showConfirmButton: false,
				timer: 2500
			});
			location.href="/signin";
		}).catch(error => {
			$scope.error = error.data;
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: $scope.error.message,
			  footer: ''
			})
		})
	}
	
})