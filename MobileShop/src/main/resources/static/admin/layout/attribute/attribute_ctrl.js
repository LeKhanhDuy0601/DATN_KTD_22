/**
 * 
 */
 app.controller("attribute-ctrl" , function($scope ,$http){
	
	$scope.form = {};
	$scope.error = {};
	$scope.attribute_groups = [];
	$scope.attributes = [];
	
	$scope.initialize = function(){
		$http.get("/rest/attribute-groups").then(resp => {
			$scope.attribute_groups = resp.data;
		});
		
		$http.get("/rest/attributes").then(resp => {
			$scope.attributes = resp.data;
			$scope.attributes.forEach(account => {
				if(account.attribute_group.type == 'TYPE_COLOR'){
					account.style = {
					"background-color" : account.value
					}	
				}
			});
		});
	}
	
	$scope.create = function(){
		var attribute = angular.copy($scope.form);
		$scope.error = {};
		$http.post(`/rest/attributes`, attribute).then(resp => {
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
			if($scope.error.type == 'value'){
				$scope.error.value = $scope.error.message;
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
		$scope.attributes = [];
		$scope.initialize();
	}
	
	$scope.update = function(){
		var attribute = angular.copy($scope.form);
		$scope.error = {};
		$http.put(`/rest/attributes`, attribute).then(resp => {
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
			if($scope.error.type == 'value'){
				$scope.error.value = $scope.error.message;
			}
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		})
	}
	
	$scope.delete = function(attribute){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa danh mục này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`,  
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
			/* Read more about isConfirmed, isDenied below */  
		    if (result.isConfirmed) {
				$scope.error = {};
				$http.put(`/rest/attributes/${attribute.id}`, attribute).then(resp=>{
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
	
	$scope.edit = function(attribute){
		$scope.form = angular.copy(attribute);
	}
	
	$scope.search = function(key_search){$http.get(`/rest/attributes/${key_search}`).then(resp => {
			$scope.attributes = resp.data;
		});
	}
	
	$scope.pager ={
        page : 0,
        size : 10,
        get attributes(){
            var start = this.page * this.size;
         	return   $scope.attributes.slice(start , start + this.size)
        },
        get count(){
            return Math.ceil(1.0 * $scope.attributes.length / this.size)
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