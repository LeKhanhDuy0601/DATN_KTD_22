app.controller("product-ctrl" , function($scope ,$http){
	$scope.form = {};
	$scope.brands = [];
	$scope.units = [];
	$scope.category_groups = [];
	$scope.categories = [];
	$scope.product_categories = [];
	$scope.products = [];
	$scope.categories_create = [];
	$scope.image = {};
	$scope.images = [];
	$scope.key_search = null;
	$scope.variant = {};
	$scope.variants = [];
	$scope.attribute_groups = [];
	$scope.attributes = [];
	$scope.price_histories = [];
	$scope.key_search_price = null;
	$scope.tab_name = 'Sản phẩm';
	$scope.error = {};
	$scope.error_variant = {};
	
	$scope.parameter = {};
	$scope.parameters = [];
	$scope.parameter_variants = [];
	$scope.error_parameter = {};
	
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
		
		$scope.image.name = '1.jpg';
		
		$scope.variant.image = '1.jpg';
		
		$http.get("/rest/brands").then(resp => {
			$scope.brands = resp.data;
		});
		
		$http.get("/rest/units").then(resp => {
			$scope.units = resp.data;
		});
		
		$http.get("/rest/category-groups").then(resp => {
			$scope.category_groups = resp.data;
			$scope.category_groups.forEach(category_group => {
				$scope.findCategoriesByCategoryGroup(category_group);
			});
		});
		
		$http.get("/rest/product-categories").then(resp => {
			$scope.product_categories = resp.data;
		});
		
		$http.get("/rest/attribute-groups").then(resp => {
			$scope.attribute_groups = resp.data;
			$scope.attribute_groups.forEach(attribute_group => {
				$scope.findAttributesByAttributeGroup(attribute_group);
			});
		});
		
		$http.get("/rest/products").then(resp => {
			$scope.products = resp.data;
			$scope.products.forEach(product => {
				$scope.findImagesByProduct(product);
			});
		});
		
		$http.get("/rest/price-histories").then(resp => {
			$scope.price_histories = resp.data;
		});
	}
	
	$scope.findCategoriesByCategoryGroup = function(category_group){
		$http.get(`/rest/categories/find-categories-by-category-group/${category_group.id}`).then(resp => {
			category_group.categories = resp.data;
		});
	}
	
	$scope.findImagesByProduct = function(product){
		$http.get(`/rest/product-images/find-images-by-product/${product.id}`).then(resp => {
			product.product_images = resp.data;
		});
	}
	
	$scope.findAttributesByAttributeGroup = function(attribute_group){
		$http.get(`/rest/attributes/find-attributes-by-attribute-group/${attribute_group.id}`).then(resp => {
			attribute_group.attributes = resp.data;
		});
	}
	
	$scope.create = function(){
		var product = angular.copy($scope.form);
		product.images = $scope.images;
		$scope.error = {};
		$http.post(`/rest/products`, product).then(resp => {
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
			if($scope.error.type == 'code'){
				$scope.error.code = $scope.error.message;
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
		$scope.brands = [];
		$scope.units = [];
		$scope.category_groups = [];
		$scope.categories = [];
		$scope.product_categories = [];
		$scope.products = [];
		$scope.categories_create = [];
		$scope.image = {};
		$scope.images = [];
		$scope.tab_name = 'Sản phẩm';
		$scope.error = {};
		$scope.initialize();
	}
	
	$scope.update = function(){
		var product = angular.copy($scope.form);
		product.images = $scope.images;
		$scope.error = {};
		$http.put(`/rest/products`, product).then(resp=>{
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
		});
	}
	
	$scope.delete = function(){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa sản phẩm này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`, 
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
		    if (result.isConfirmed) {
			    var product = angular.copy($scope.form);
				$scope.error = {};
				$http.put(`/rest/products/${product.id}`, product).then(resp=>{
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
					  text: $scope.error.message,
					  footer: ''
					})
				})    
			} else {    
		    	Swal.fire('Bạn đã hủy việc xóa!', '', 'info')  
		 	}
		});
	}
	
	$scope.edit = function(product){
		$scope.form = angular.copy(product);
		$scope.form.product_images.forEach(image => {
			$scope.images.push(image);
		});
		$scope.tab_name_change.tab_product();
		$(".nav-pills button:eq(0)").tab('show');
	}
	
	$scope.updatePrice = function(){
		Swal.fire({  
		  title: 'Bạn có chắc chắn cập nhật giá này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`, 
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
		    if (result.isConfirmed) {
			    var product = angular.copy($scope.form);
				$scope.error = {};
				$http.post(`/rest/price-histories/product`, product).then(resp=>{
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
			} else {    
		    	Swal.fire('Bạn đã hủy việc cập nhật!', '', 'info')  
		 	}
		});
	}
	
	$scope.product_category_of = function(category){
		if($scope.form.id){
			if($scope.product_categories){
				return $scope.product_categories
					.find(product_category => product_category.product.id == $scope.form.id && product_category.category.id == category.id);
			}
		}
	}
	
	$scope.product_category_changed = function(category){
		if($scope.form.id){
			var product_category = $scope.product_category_of(category);
			if(product_category){
				$scope.revoke_product_category(product_category);
			}
			if(!product_category){
				product_category = {product:$scope.form, category:category};
				$scope.grant_product_category(product_category);
			}
		}
		if(!$scope.form.id){
			var check_category = $scope.categories_create.find(category_create => category_create.id == category.id);
			if(check_category){
				var index = $scope.categories_create.findIndex(category_create => category_create.id == category.id);
				$scope.categories_create.splice(index, 1);
			}
			if(!check_category){
				$scope.categories_create.push(category);
			}
			$scope.form.categories = $scope.categories_create;
		}
	}
	
	$scope.grant_product_category = function(product_category){
		$http.post('/rest/product-categories/', product_category).then(resp => {
			$scope.initialize();
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: 'Chọn danh mục thành công!',
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Chọn danh mục thất bại!',
			  footer: error
			})
		});
	}
	
	$scope.revoke_product_category = function(product_category){
		$http.delete(`/rest/product-categories/${product_category.id}`).then(resp => {
			$scope.initialize();
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: "Bỏ chọn danh mục thành công!",
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Bỏ chọn danh mục thất bại!',
			  footer: error
			})
		});
	}
	
	$scope.search = function(key_search){
		$http.get(`/rest/products/${key_search}`).then(resp => {
			$scope.products = resp.data;
		});
	}
	
	$scope.imagesChanged = function(files){
		var data = new FormData();
		data.append('file', files[0]);
		$scope.image = {};
		$http.post('/rest/upload/products', data, {
			transformRequest:angular.identity,
			headers:{'Content-Type': undefined}
		}).then(resp => {
			if($scope.form.id){
				$scope.image.name = resp.data.name;
				$scope.image.product = $scope.form;
				$scope.images.push($scope.image);
			}
			if(!$scope.form.id){
				$scope.image.name = resp.data.name;
				$scope.images.push($scope.image);
			}
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
	
	$scope.resetImages = function() {
		$scope.image = {};
		$scope.image.name = '1.jpg';
	}
	
	$scope.removeImageFromFolder = function(image){
		$http.delete(`/rest/delete/products/${image.name}`).then(resp => {
			
		}).catch(error => {
			Swal.fire({
			  icon: 'error',
			  title: 'LỖI',
			  text: 'Đã xảy ra sự cố!',
			  footer: error
			})
		});
	}
	
	$scope.deleteImage = function(image){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa ảnh này?',  
		  showCancelButton: true, 
		  cancelButtonText: `Hủy`, 
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
			/* Read more about isConfirmed, isDenied below */  
		    if (result.isConfirmed) {
				if(!$scope.form.id){
					$scope.removeImageFromFolder(image);
					var index = $scope.images.findIndex(img => img.name === image.name);
					$scope.images.splice(index, 1);
					$scope.resetImages();	
				}
				if($scope.form.id){
					if(!image.id){
						$scope.removeImageFromFolder(image);
						var index = $scope.images.findIndex(img => img.name === image.name);
						$scope.images.splice(index, 1);
						$scope.resetImages();	
					}
					if(image.id){
						$http.delete(`/rest/product-images/${image.id}`).then(resp => {
							$scope.removeImageFromFolder(image);
							var index = $scope.images.findIndex(img => img.id === image.id);
							$scope.images.splice(index, 1);
							$scope.resetImages();
							Swal.fire({
								position: 'center',
								icon: 'success',
								title: "Xóa hình ảnh thành công!",
								showConfirmButton: false,
								timer: 2500
							})
						}).catch(error => {
							Swal.fire({
							  icon: 'error',
							  title: 'LỖI',
							  text: 'Xóa hình ảnh thất bại!',
							  footer: error
							})
						});
					}
				}
			} else {    
		    	Swal.fire('Bạn đã hủy việc xóa!', '', 'info')  
		 	}
		});
	}
	
	$scope.viewImage = function(image){
		$scope.image = image;
	}
	
	//Product Variant
	$scope.createVariant = function(){
		var variant = angular.copy($scope.variant);
		$scope.error_variant = {};
		$http.post(`/rest/product-variants`, variant).then(resp => {
			$scope.resetVariant(variant.product);
			$scope.viewVariant(variant.product);
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: resp.data.message,
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			$scope.error_variant = error.data;
			if(error.data.type === 'image'){
				$scope.error_variant.image = error.data.message;
				Swal.fire({
				  icon: 'error',
				  title: 'LỖI',
				  text: 'Đã xảy ra sự cố!',
				  footer: ''
				})
			}
			if(error.data.type === 'variant'){
				$scope.error_variant.variant = error.data.message;
				Swal.fire({
				  icon: 'error',
				  title: 'LỖI',
				  text: error.data.message,
				  footer: ''
				})
			}
		});
	}
	
	$scope.resetVariant = function(product){
		$scope.variant = {};
		$scope.attributes = [];
		$scope.variant.product = product;
		$scope.variant.image = '1.jpg';
		$scope.error_variant = {};
	}
	
	$scope.resetVariantAll = function(){
		$scope.variant = {};
		$scope.variants = [];
		$scope.variant.image = '1.jpg';
		$scope.attributes = [];
		$scope.error_variant = {};
	}
	
	$scope.updateVariant = function(){
		var variant = angular.copy($scope.variant);
		$scope.error_variant = {};
		$http.put(`/rest/product-variants`, variant).then(resp => {
			$scope.viewVariant(variant.product);
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: resp.data.message,
				showConfirmButton: false,
				timer: 2500
			})
		}).catch(error => {
			$scope.error_variant = error.data;
			if(error.data.type === 'image'){
				$scope.error_variant.image = error.data.message;
				Swal.fire({
				  icon: 'error',
				  title: 'LỖI',
				  text: 'Đã xảy ra sự cố!',
				  footer: ''
				})
			}
			if(error.data.type === 'variant'){
				$scope.error_variant.variant = error.data.message;
				Swal.fire({
				  icon: 'error',
				  title: 'LỖI',
				  text: error.data.message,
				  footer: ''
				})
			}
		});
	}
	
	$scope.updatePriceVariant = function(){
		Swal.fire({  
		  title: 'Bạn có chắc chắn cập nhật giá này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`, 
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
		    if (result.isConfirmed) {
			    var variant = angular.copy($scope.variant);
				$scope.error_variant = {};
				$http.post(`/rest/price-histories/variant`, variant).then(resp=>{
					$scope.viewVariant(variant.product);
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
			} else {    
		    	Swal.fire('Bạn đã hủy việc cập nhật!', '', 'info')  
		 	}
		});
	}
	
	$scope.deleteVariant = function(){
		Swal.fire({  
		  title: 'Bạn có chắc chắn xóa biến thể này?',  
		  showCancelButton: true,
		  cancelButtonText: `Hủy`, 
		  confirmButtonText: `Xác nhận`,  
		}).then((result) => {  
		    if (result.isConfirmed) {
			    var variant = angular.copy($scope.variant);
				$scope.error_variant = {};
				$http.put(`/rest/product-variants/${variant.id}`, variant).then(resp => {
					$scope.viewVariant(variant.product);
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
					  text: $scope.error.message,
					  footer: ''
					})
				})    
			} else {    
		    	Swal.fire('Bạn đã hủy việc xóa!', '', 'info')  
		 	}
		});
	}
	
	$scope.viewVariant = function(product){
		$scope.variant = {};
		$scope.variants = [];
		$scope.attributes = [];
		$scope.variant.product = product;
		$scope.variant.image = '1.jpg';
		$http.get(`/rest/product-variants/variants-by-product/${product.id}`).then(resp => {
			$scope.variants = resp.data;
		});
		$scope.tab_name_change.tab_attribute();
		$(".nav-pills button:eq(1)").tab('show');
	}
	
	$scope.editVariant = function(variant){
		$scope.variant = variant;
		$scope.attributes = $scope.variant.attributes;
	}
	
	$scope.attribute_of = function(attribute){
		return $scope.attributes.find(ab => ab.id === attribute.id);
	}
	
	$scope.chooseAttribute = function(attribute){
		var check_attribute = $scope.attribute_of(attribute);
		if(check_attribute){
			var index = $scope.attributes.findIndex(ab => ab.id === attribute.id);
			$scope.attributes.splice(index, 1);
		}
		if(!check_attribute){
			$scope.attributes.forEach(ab => {
				if(ab.attribute_group.id === attribute.attribute_group.id){
					var index = $scope.attributes.findIndex(atb => atb.id === ab.id);
					$scope.attributes.splice(index, 1);
				}
			});
			$scope.attributes.push(attribute);
		}
		$scope.variant.attributes = $scope.attributes;
	}
	
	$scope.search_price_histories = function(key_search_price){
		$http.get(`/rest/price-histories/${key_search_price}`).then(resp => {
			$scope.price_histories = resp.data;
		});
	}
	
	$scope.imageVariantChanged = function(files){
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/variants', data, {
			transformRequest:angular.identity,
			headers:{'Content-Type': undefined}
		}).then(resp => {
			$scope.variant.image = null;
			$scope.variant.image = resp.data.name;
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
	
	//Parameter
	
	$scope.viewParameter = function(product){
		$scope.parameter = {};
		$scope.parameters = [];
		$scope.parameter.product = product;
		$http.get(`/rest/product-variants/variants-by-product/${product.id}`).then(resp => {
			$scope.parameter_variants = resp.data;
		});
		
		$http.get(`/rest/product-parameters/${product.id}`).then(resp => {
			$scope.parameters = resp.data;
		});
		
		$scope.tab_name_change.tab_attribute();
		$(".nav-pills button:eq(2)").tab('show');
	}
	
	$scope.tab_name_change = {
		tab_product(){
			$scope.tab_name = 'Sản phẩm';
		},
		tab_attribute(){
			$scope.tab_name = 'Thuộc tính - Biến thể';
		},
		tab_parameter(){
			$scope.tab_name = 'Thông số kỹ thuật';
		},
		tab_price(){
			$scope.tab_name = 'Lịch sử giá';
		},
		tab_list(){
			$scope.tab_name = 'Danh sách sản phẩm';
		}
	}
	
	$scope.pager ={
        page : 0,
        size : 10,
        get products(){
            var start = this.page * this.size;
         	return   $scope.products.slice(start , start + this.size)
        },
        get count(){
            return Math.ceil(1.0 * $scope.products.length / this.size)
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
    
    $scope.pager_price_histories ={
        page : 0,
        size : 10,
        get price_histories(){
            var start = this.page * this.size;
         	return   $scope.price_histories.slice(start , start + this.size)
        },
        get count(){
            return Math.ceil(1.0 * $scope.price_histories.length / this.size)
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