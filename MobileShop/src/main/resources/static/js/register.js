const app = angular.module("register-app", []);
app.controller("register-ctrl", function($scope, $http){
	
	$scope.form = {};
	
	$scope.register = function(){
		var account = angular.copy($scope.form);
		account.roles = ['guest'];
		$scope.error = null;
		$http.post(`/rest/accounts/register`, account).then(resp => {
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
			if($scope.error.type == 'confirm_password'){
				$scope.error.confirm_password = $scope.error.message;
			}
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		})
	}
	
})