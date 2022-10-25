/**
 * 
 */
 app.controller("parameter-group-ctrl" , function($scope ,$http){
	
	$scope.form = {};
	$scope.parameter_groups = [];
	$scope.error = {};
	
	$scope.initialize = function(){
		
		$http.get("/rest/parameter-groups").then(resp => {
			$scope.parameter_groups = resp.data;
		});
	}
	
	$scope.create = function(){
		var parameter_group = angular.copy($scope.form);
		$scope.error = {};
		$http.post(`/rest/parameter-groups`, parameter_group).then(resp => {
			$scope.reset();
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: resp.data.message,
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			$scope.error = error.data;
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		})
	}
	
	$scope.reset = function(){
		$scope.form = {};
		$scope.parameter_groups = [];
		$scope.error = {};
		$scope.initialize();
	}
	
	$scope.update = function(){
		var parameter_group = angular.copy($scope.form);
		$scope.error = {};
		$http.put(`/rest/parameter-groups`, parameter_group).then(resp => {
			$scope.reset();
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: resp.data.message,
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			$scope.error = error.data;
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		})
	}
	
	$scope.delete = function(parameter_group){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa nhóm danh mục này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`,  
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
			/* Read more about isConfirmed, isDenied below */  
		    if (result.isConfirmed) {
				$scope.error = {};
				$http.put(`/rest/parameter-groups/${parameter_group.id}`, parameter_group).then(resp=>{
					$scope.reset();
					Swal.fire({
						position: 'center',
						icon: 'success',
						title: resp.data.message,
						showConfirmButton: false,
						timer: 2500
					})
				}).catch(error=>{
					$scope.error = error.data;
					Swal.fire({
					  icon: 'error',
					  title: 'LỖI',
					  text: 'Đã xảy ra sự cố!',
					  footer: ''
					})
				})    
		    	/* Swal.fire('Saved!', '', 'success') */ 
		    } else {    
		    	Swal.fire('Bạn đã hủy việc xóa!', '', 'info')  
		 	}
		});
	}
	
	$scope.edit = function(parameter_group){
		$scope.form = angular.copy(parameter_group);
	}
	
	$scope.initialize();
});