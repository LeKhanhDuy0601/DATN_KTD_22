/**
 * 
 */

app = angular.module("admin-app", ['ngRoute','ng.ckeditor']);
app.config(function($routeProvider) {
    $routeProvider
        .when("/accounts", {
            templateUrl: "/admin/layout/account/account_page.html",
            controller: "account-ctrl"
        })
        .when("/attributes" ,{
	        templateUrl :"/admin/layout/attribute/attribute_page.html",
			 controller: "attribute-ctrl"
	        
	    })
	    .when("/products" ,{
	        templateUrl :"/admin/layout/product/product_page.html",
			 controller: "product-ctrl"
	        
	    })
        .when("/attribute_groups" ,{
        templateUrl :"/admin/layout/attribute_group/attribute_group_page.html",
		 controller: "attribute-group-ctrl"
        
    	})
        .when("/categories" ,{
	        templateUrl :"/admin/layout/category/category_page.html",
			 controller: "category-ctrl"
	        
	    })
         .when("/category_groups" ,{
	        templateUrl :"/admin/layout/category_group/category_group_page.html",
			 controller: "category-group-ctrl"
	        
	    })
	    .when("/brands" ,{
	        templateUrl :"/admin/layout/brand/brand_page.html",
			 controller: "brand-ctrl"
        
    	})
    	.when("/parameter_groups" ,{
	        templateUrl :"/admin/layout/parameter_group/parameter_group_page.html",
			 controller: "parameter-group-ctrl"
        
    	})
    	.when("/parameters" ,{
	        templateUrl :"/admin/layout/parameter/parameter_page.html",
			 controller: "parameter-ctrl"
        
    	})
        .otherwise({
            templateUrl: "/assets/admin/thongke/thongke.html",
            controller: "thongke-ctrl"
        })
})