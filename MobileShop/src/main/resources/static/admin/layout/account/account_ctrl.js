app.controller("account-ctrl" , function($scope ,$http){
	
	$scope.form = {};
	$scope.accounts = [];
	$scope.cities = [];
	$scope.districts = [];
	$scope.wards = [];
	$scope.roles = [];
	$scope.authorities = [];
	$scope.error = {};
	$scope.sum_message = null;
	$scope.roles_create = [];
	$scope.checked_deleted = null;
	$scope.checked_blocked = null;
	$scope.checked_open = null;
	$scope.checked_confirm = false;
	$scope.key_search = null;
	$scope.tab_name = 'Tài khoản';
	
	$scope.initialize = function(){
		$scope.form.avatar = 'avatar.jpg';
		
		$http.get("/rest/accounts").then(resp => {
			$scope.accounts = resp.data;
			$scope.accounts.forEach(account => {
				account.birthday = new Date(account.birthday)
			});
		});
		
		$http.get("/rest/cities").then(resp => {
			$scope.cities = resp.data;
		});
		
		$http.get("/rest/roles").then(resp =>{
             $scope.roles = resp.data;
        });
        
        $http.get("/rest/authorities").then(resp => {
			$scope.authorities=resp.data;
		}).catch(error => {
			$location.path("/unauthorized");
		})
	}
	
	$scope.create = function(){
		var account = angular.copy($scope.form);
		$scope.error = {};
		$scope.sum_message = null;
		$scope.checked_deleted = null;
		$scope.checked_blocked = null;
		$scope.checked_open = null;
		$http.post(`/rest/accounts`, account).then(resp => {
			$scope.reset();
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: resp.data.message,
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			$scope.error = error.data
			if($scope.error.type == 'username'){
				$scope.error.username = $scope.error.message;
			}
			if($scope.error.type == 'phone'){
				$scope.error.phone = $scope.error.message;
			}
			if($scope.error.type == 'email'){
				$scope.error.email = $scope.error.message;
			}
			if($scope.error.type == 'confirm_password'){
				$scope.error.confirm_password = $scope.error.message;
			}
			if($scope.error.roles){
				$scope.sum_message++;
			}
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: ''
			})
		});
	}
	
	$scope.reset = function(){
		$scope.form = {};
		$scope.accounts = [];
		$scope.cities = [];
		$scope.districts = [];
		$scope.wards = [];
		$scope.roles = [];
		$scope.authorities = [];
		$scope.error = {};
		$scope.sum_message = null;
		$scope.roles_create = [];
		$scope.checked_deleted = null;
		$scope.checked_blocked = null;
		$scope.checked_open = null;
		$scope.checked_confirm = false;
		$scope.key_search = null;
		$scope.initialize();
	}
	
	$scope.update = function(){
		var account = angular.copy($scope.form);
		$scope.error = {};
		$scope.sum_message = null;
		$scope.checked_deleted = null;
		$scope.checked_blocked = null;
		$scope.checked_open = null;
		$http.put(`/rest/accounts`, account).then(resp=>{
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
			if($scope.error.type == 'username'){
				$scope.error.username = $scope.error.message;
			}
			if($scope.error.type == 'phone'){
				$scope.error.phone = $scope.error.message;
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
	
	$scope.delete = function(checked_confirm){
		var account = angular.copy($scope.form);
		$scope.error = {};
		$scope.sum_message = null;
		var account_delete = {
			id: account.id,
			confirm: checked_confirm
		};
		$http.put(`/rest/accounts/${account.id}`, account_delete).then(resp=>{
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
	}
	
	$scope.deleted_click = function(){
		$scope.error = {};
		$scope.sum_message = null;
		$scope.checked_blocked = null;
		$scope.checked_open = null;
		$scope.checked_confirm = false;
		$scope.checked_deleted = 1;
	}
	
	$scope.block = function(checked_confirm){
		var account = angular.copy($scope.form);
		$scope.error = {};
		$scope.sum_message = null;
		var account_block = {
			id: account.id,
			confirm: checked_confirm
		};
		$http.put(`/rest/accounts/block/${account.id}`, account_block).then(resp=>{
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
	}
	
	$scope.blocked_click = function(){
		$scope.error = {};
		$scope.sum_message = null;
		$scope.checked_deleted = null;
		$scope.checked_open = null;
		$scope.checked_confirm = false;
		$scope.checked_blocked = 1;
	}
	
	$scope.open = function(checked_confirm){
		var account = angular.copy($scope.form);
		$scope.error = {};
		$scope.sum_message = null;
		var account_open = {
			id: account.id,
			confirm: checked_confirm
		};
		$http.put(`/rest/accounts/open/${account.id}`, account_open).then(resp=>{
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
	}
	
	$scope.open_click = function(){
		$scope.error = {};
		$scope.sum_message = null;
		$scope.checked_deleted = null;
		$scope.checked_confirm = false;
		$scope.checked_blocked = null;
		$scope.checked_open = 1;
	}
	
	$scope.reset_deleted_blocked = function(){
		$scope.error = {};
		$scope.sum_message = null;
		$scope.checked_deleted = null;
		$scope.checked_blocked = null;
		$scope.checked_open = null;
		$scope.checked_confirm = false;
	}
	
	$scope.search = function(key_search){
		$http.get(`/rest/accounts/${key_search}`).then(resp => {
			$scope.accounts = resp.data;
			$scope.accounts.forEach(account => {
				account.birthday = new Date(account.birthday)
			});
		});
	}
	
	$scope.edit = function(account){
		$scope.form = angular.copy(account);
		$(".nav-pills button:eq(0)").tab('show');
	}
	
	$scope.authority_of = function(role){
		if($scope.form.id){
			if($scope.authorities){
				return $scope.authorities
					.find(authority => authority.account.id == $scope.form.id && authority.role.id == role.id);
			}
		}
	}
	
	$scope.authority_changed = function(role){
		if($scope.form.id){
			var authority = $scope.authority_of(role);
			if(authority){
				$scope.revoke_authority(authority);
			} else {
				authority = {account:$scope.form, role:role};
				$scope.grant_authority(authority);
			}
		}
		if(!$scope.form.id){
			var check_role = $scope.roles_create.find(role_create => role_create.id==role.id);
			if(check_role){
				var index = $scope.roles_create.findIndex(role_create => role_create.id == role.id);
				$scope.roles_create.splice(index, 1);
			}
			if(!check_role){
				$scope.roles_create.push(role);
			}
			$scope.form.roles = $scope.roles_create;
		}
	}
	
	$scope.grant_authority = function(authority){
		$http.post('/rest/authorities/', authority).then(resp => {
			$scope.initialize();
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: 'Cấp quyền thành công!',
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Cấp quyền thất bại!',
			  footer: error
			})
		});
	}
	
	$scope.revoke_authority = function(authority){
		$http.delete(`/rest/authorities/${authority.id}`).then(resp => {
			$scope.initialize();
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: "Thu hồi quyền thành công!",
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Thu hồi quyền thất bại!',
			  footer: error
			})
		});
	}
	
	$scope.getDistrictsByCity = function(city){
		$scope.districts = [];
		$scope.wards = [];
		if(city != null){
			$http.get(`/rest/districts/`+city.id).then(resp => {
				$scope.districts = resp.data;
			});
		}
	}
	
	$scope.getWardsByDistrict = function(district){
		$scope.wards = [];
		if(district != null){
			$http.get(`/rest/wards/`+district.id).then(resp => {
				$scope.wards = resp.data;
			});
		}
	}
	
	$scope.avatarChanged = function(files){
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/avatars', data, {
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
		$scope.form.avatar = 'avatar.jpg';
	}
	
	$scope.tab_name_change = {
		tab_account(){
			$scope.tab_name = 'Tài khoản';
		},
		tab_authority(){
			$scope.tab_name = 'Phân quyền';
		},
		tab_list(){
			$scope.tab_name = 'Danh sách tài khoản';
		}
	}
	
	$scope.pager ={
        page : 0,
        size : 10,
        get accounts(){
            var start = this.page * this.size;
         	return   $scope.accounts.slice(start , start + this.size)
        },
        get count(){
            return Math.ceil(1.0 * $scope.accounts.length / this.size)
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