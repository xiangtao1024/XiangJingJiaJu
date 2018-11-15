var Apicheck = {
	getTime : function(){
		//var date = new Date();
		//return Math.floor(date.getTime() / 1000 / 60);
		return 1000;
	},
	getAuthKey : function(){
		return "lxvjX1T9Ylb2boK0lqAuv6";
	},
	getSign : function(){
		var time = this.getTime();
		return hex_md5(hex_md5(this.getAuthKey() + time) + time)
	},
	getHeaders: function(){
		return {"AUTH_KEY":this.getAuthKey(),"AUTH_VALUE":this.getSign()};
	}
};

var Application = {
	openNav: function(index){
		switch(index){
			case "1":
				location.href = "/admin/index.html";
				break;
			case "2":
				location.href = "/admin/category/index.html";
				break;
			case "3":
				location.href = "/admin/scene/index.html";
				break;
			case "4":
				location.href = "/admin/label/index.html";
				break;
			case "5":
				location.href = "/admin/sentense/index.html";
				break;
			case "6":
				location.href = "/admin/user/index.html";
				break;
			case "7":
				location.href = "/admin/comment/index.html";
				break;
			default:
				console.log(index,"default")
				break;
		}
		
	}
}

