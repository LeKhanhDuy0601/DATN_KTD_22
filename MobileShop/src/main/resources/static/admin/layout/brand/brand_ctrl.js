app.controller("brand-ctrl" , function($scope ,$http){
	
	$scope.form = {};
	$scope.brands = [];
	$scope.error = {};
	$scope.config = {};
	$scope.config.toolbarGroups = [
			{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
			{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
			{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
			{ name: 'forms', groups: [ 'forms' ] },
			{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
			{ name: 'links', groups: [ 'links' ] },
			{ name: 'insert', groups: [ 'insert' ] },
			{ name: 'styles', groups: [ 'styles' ] },
			{ name: 'colors', groups: [ 'colors' ] },
			{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
			{ name: 'tools', groups: [ 'tools' ] },
			{ name: 'others', groups: [ 'others' ] },
			{ name: 'about', groups: [ 'about' ] }
	];
	$scope.config.removeButtons = 'BGColor,Anchor,Subscript,Superscript,Paste,Copy,Cut,Undo,Redo';
	
	$scope.initialize = function(){
		$scope.form.avatar = 'favicon.ico';
		
		$http.get("/rest/brands").then(resp => {
			$scope.brands = resp.data;
		});
	}
	
	$scope.create = function(){
		var brand = angular.copy($scope.form);
		$scope.error = {};
		$http.post(`/rest/brands`, brand).then(resp => {
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
			if($scope.error.type == 'avatar'){
				$scope.error.avatar = $scope.error.message;
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
		$scope.brands = [];
		$scope.error = {};
		$scope.initialize();
	}
	
	$scope.update = function(){
		var brand = angular.copy($scope.form);
		$scope.error = {};
		$http.put(`/rest/brands`, brand).then(resp => {
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
			if($scope.error.type == 'avatar'){
				$scope.error.avatar = $scope.error.message;
			}
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		})
	}
	
	$scope.delete = function(brand){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa thương hiệu này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`, 
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
			/* Read more about isConfirmed, isDenied below */  
		    if (result.isConfirmed) {
				$scope.error = {};
				$http.put(`/rest/brands/${brand.id}`, brand).then(resp=>{
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
	
	$scope.edit = function(brand){
		$scope.form = angular.copy(brand);
	}
	
	$scope.search = function(key_search){
		$http.get(`/rest/brands/${key_search}`).then(resp => {
			$scope.brands = resp.data;
		});
	}
	
	$scope.avatarChanged = function(files){
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/brands', data, {
			transformRequest:angular.identity,
			headers:{'Content-Type': undefined}
		}).then(resp => {
			$scope.form.avatar=resp.data.name;
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
	
	$scope.resetAvatar = function() {
		$scope.form.avatar = 'favicon.ico';
	}
	
	$scope.pager ={
        page : 0,
        size : 10,
        get brands(){
            var start = this.page * this.size;
         	return   $scope.brands.slice(start , start + this.size)
        },
        get count(){
            return Math.ceil(1.0 * $scope.brands.length / this.size)
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