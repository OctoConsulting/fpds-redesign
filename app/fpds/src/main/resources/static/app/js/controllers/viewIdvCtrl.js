module.exports = function($scope,$http,$location,$routeParams,$anchorScroll,SearchFactory){
	$scope.idvnumber = $routeParams.idvId;
	$scope.loading = 0;
	$scope.expanded = [];
	$scope.flag = [];

	$scope.company = function(value){
		console.log(value);
		return SearchFactory.getAutocomplete(value).then(function(res){
			return res.data.map(function(item){
				return item;
			});
		});
	};

	$scope.search = function(){
		var earl = '/search/' + $scope.query;
    	$location.path(earl);
	};

	$scope.back = function(){
		var rl = '/search/' + $scope.vendor;
		$location.path(rl);
	};

	var results = [];
	
	SearchFactory.getViewIdv($scope.idvnumber)
	.success(function(data){
		//console.log(data);
		btnArray(data);
		$scope.loading = 1;
	})
	.error(function(header,status,config,data){
		console.log(status);
	});

		
	btnArray = function(data){
		_.forEach(data, function (value, key) {
  			//console.log("Here");
 			results.push({"piid" : key, "contracts" : value});
 		});

		$scope.contractDetails = results;
		var mainButtons = $scope.contractDetails.length;
		$scope.vendor = $scope.contractDetails[0].contracts[0].vendorname;
		console.log($scope.vendor);
		//console.log("Main " +mainButtons);

		for(var i = 0 ; i< mainButtons; i++){
			$scope.expanded[i] = false;
		}

		for(var x = 0; x < $scope.contractDetails.length ; x++){
			$scope.flag[x] = [];
			for(var y = 0; y < $scope.contractDetails[x].contracts.length; y++){
				$scope.flag[x][y] = false;
			}
		}

	};
	$scope.gototop = function(){
		$location.hash('top2');
		$anchorScroll();
	};
  		
	var innerMax;
	//console.log(innerMax);

	

	$scope.expand = function(val){
		$scope.expanded[val] = !$scope.expanded[val];
		for(var i = 0; i< $scope.contractDetails[val].contracts.length; i++){
			if($scope.expanded[val] === true){
				$scope.flag[val][i] = true;
			}
			else{
				$scope.flag[val][i] = false;
			}
		}
	};

	$scope.tranExpand = function(parent,child){
		//console.log("Parent " + parent +"Child " + child);
		$scope.flag[parent][child] = !$scope.flag[parent][child];
	};
};