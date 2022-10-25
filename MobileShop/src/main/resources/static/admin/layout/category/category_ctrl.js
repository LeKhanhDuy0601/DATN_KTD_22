app.controller("category-ctrl" , function($scope ,$http){
	
	$scope.form = {};
	$scope.categories = [];
	$scope.category_groups = [];
	$scope.error = {};
	
	$scope.initialize = function(){
		
		$http.get("/rest/categories").then(resp => {
			$scope.categories = resp.data;
		});
		
		$http.get("/rest/category-groups").then(resp => {
			$scope.category_groups = resp.data;
		});
	}
	
	$scope.create = function(){
		var category = angular.copy($scope.form);
		$scope.error = {};
		$http.post(`/rest/categories`, category).then(resp => {
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
		$scope.categories = [];
		$scope.category_groups = [];
		$scope.error = {};
		$scope.initialize();
	}
	
	$scope.update = function(){
		var category = angular.copy($scope.form);
		$scope.error = {};
		$http.put(`/rest/categories`, category).then(resp => {
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
	
	$scope.delete = function(category){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa danh mục này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`, 
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
			/* Read more about isConfirmed, isDenied below */  
		    if (result.isConfirmed) {
				$scope.error = {};
				$http.put(`/rest/categories/${category.id}`, category).then(resp=>{
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
	
	$scope.edit = function(category){
		$scope.form = angular.copy(category);
	}
	
	$scope.search = function(key_search){
		$http.get(`/rest/categories/${key_search}`).then(resp => {
			$scope.categories = resp.data;
		});
	}
	
	$scope.pager ={
        page : 0,
        size : 10,
        get categories(){
            var start = this.page * this.size;
         	return   $scope.categories.slice(start , start + this.size)
        },
        get count(){
            return Math.ceil(1.0 * $scope.categories.length / this.size)
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