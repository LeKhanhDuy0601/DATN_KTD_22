app.controller("attribute-group-ctrl" , function($scope ,$http){
	
	$scope.form = {};
	$scope.error = {};
	$scope.attribute_groups = [];
	
	$scope.initialize = function(){
		$http.get("/rest/attribute-groups").then(resp => {
			$scope.attribute_groups = resp.data;
		});
	}
	
	$scope.create = function(){
		var attribute_group = angular.copy($scope.form);
		$scope.error = {};
		$http.post(`/rest/attribute-groups`, attribute_group).then(resp => {
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
		$scope.error = {};
		$scope.attribute_groups = [];
		$scope.initialize();
	}
	
	$scope.update = function(){
		var attribute_group = angular.copy($scope.form);
		$scope.error = {};
		$http.put(`/rest/attribute-groups`, attribute_group).then(resp => {
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
	
	$scope.delete = function(attribute_group){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa danh mục này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`,
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
			/* Read more about isConfirmed, isDenied below */  
		    if (result.isConfirmed) {
				$scope.error = {};
				$http.put(`/rest/attribute-groups/${attribute_group.id}`, attribute_group).then(resp=>{
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
	
	$scope.edit = function(attribute_group){
		$scope.form = angular.copy(attribute_group);
	}
	
	$scope.search = function(key_search){
		$http.get(`/rest/attribute-groups/${key_search}`).then(resp => {
			$scope.attribute_groups = resp.data;
		});
	}
	
	$scope.pager ={
        page : 0,
        size : 10,
        get attribute_groups(){
            var start = this.page * this.size;
         	return   $scope.attribute_groups.slice(start , start + this.size)
        },
        get count(){return Math.ceil(1.0 * $scope.attribute_groups.length / this.size)
        },
        first(){
           this.page = 0;
        },
        prev(){
            this.page--;
            if(this.page < 0){
                this.last();
            }
        },
        next(){
            this.page++;
            if(this.page >= this.count){
                this.first();
            }
        },
        last(){
            this.page = this.count -1 
        }
    }
	
	$scope.initialize();
});