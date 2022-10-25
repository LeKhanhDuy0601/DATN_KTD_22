	const app = angular.module("signin-app", []);
app.controller("signin-ctrl", function($scope, $http){
	
	$scope.form = {};
	
	$scope.signin = function(){
		var account = angular.copy($scope.form);
		$scope.error = null;
		
		$http.post(`/rest/accounts/signin`, account).then(resp => {
			var token = JSON.stringify(resp.data.token);
			localStorage.setItem("token", token);
			
			var roles = JSON.stringify(resp.data.roles);
			localStorage.setItem("roles", roles);
			console.log(resp.data);
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: 'Đăng nhập thành công!',
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			$scope.error = error.data
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		})
	}
	
})
;