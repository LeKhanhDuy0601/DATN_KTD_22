/**
 * 
 */
 app.controller("parameter-ctrl" , function($scope ,$http){
	
	$scope.form = {};
	$scope.parameter_groups = [];
	$scope.parameters = [];
	$scope.error = {};
	
	$scope.initialize = function(){
		
		$http.get("/rest/parameter-groups").then(resp => {
			$scope.parameter_groups = resp.data;
		});
		
		$http.get("/rest/parameters").then(resp => {
			$scope.parameters = resp.data;
		});
	}
	
	$scope.create = function(){
		var parameter = angular.copy($scope.form);
		$scope.error = {};
		$http.post(`/rest/parameters`, parameter).then(resp => {
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
		var parameter = angular.copy($scope.form);
		$scope.error = {};
		$http.put(`/rest/parameters`, parameter).then(resp => {
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
	
	$scope.delete = function(parameter){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa nhóm danh mục này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`,  
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
			/* Read more about isConfirmed, isDenied below */  
		    if (result.isConfirmed) {
				$scope.error = {};
				$http.put(`/rest/parameters/${parameter.id}`, parameter).then(resp=>{
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
	
	$scope.edit = function(parameter){
		$scope.form = angular.copy(parameter);
	}
	
	$scope.search = function(key_search){
		$http.get(`/rest/parameters/${key_search}`).then(resp => {
			$scope.parameters = resp.data;
		});
	}
	
	$scope.pager ={
        page : 0,
        size : 10,
        get parameters(){
            var start = this.page * this.size;
         	return   $scope.parameters.slice(start , start + this.size)
        },
        get count(){
            return Math.ceil(1.0 * $scope.parameters.length / this.size)
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