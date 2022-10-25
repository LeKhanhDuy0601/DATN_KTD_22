/**
 * 
 */
 app.controller("category-group-ctrl" , function($scope ,$http){
	
	$scope.form = {};
	$scope.category_groups = [];
	$scope.error = {};
	
	$scope.initialize = function(){
		$scope.form.icon = 'mailchimp.png';
		
		$http.get("/rest/category-groups").then(resp => {
			$scope.category_groups = resp.data;
		});
	}
	
	$scope.create = function(){
		var category_group = angular.copy($scope.form);
		$scope.error = {};
		$http.post(`/rest/category-groups`, category_group).then(resp => {
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
			if($scope.error.type == 'icon'){
				$scope.error.icon = $scope.error.message;
			}
			if($scope.error.type == 'code'){
				$scope.error.code = $scope.error.message;
			}
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
		$scope.category_groups = [];
		$scope.error = {};
		$scope.initialize();
	}
	
	$scope.update = function(){
		var category_group = angular.copy($scope.form);
		$scope.error = {};
		$http.put(`/rest/category-groups`, category_group).then(resp => {
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
			if($scope.error.type == 'icon'){
				$scope.error.icon = $scope.error.message;
			}
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		})
	}
	
	$scope.delete = function(category_group){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa nhóm danh mục này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`,  
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
			/* Read more about isConfirmed, isDenied below */  
		    if (result.isConfirmed) {
				$scope.error = {};
				$http.put(`/rest/category-groups/${category_group.id}`, category_group).then(resp=>{
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
	
	$scope.edit = function(category_group){
		$scope.form = angular.copy(category_group);
	}
	
	$scope.iconChanged = function(files){
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/icons', data, {
			transformRequest:angular.identity,
			headers:{'Content-Type': undefined}
		}).then(resp => {
			$scope.form.icon=resp.data.name;
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: 'Tải hình ảnh thành công!',
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: error
			})
		})
	}
	
	$scope.resetIcon = function() {
		$scope.form.icon = 'mailchimp.png';
	}
	
	$scope.initialize();
});