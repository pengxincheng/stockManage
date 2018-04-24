function initPagination(url, currentPage, totalPages) {

	var options = {
		alignment : "right",
		currentPage : currentPage,//当前页数
		totalPages : totalPages,//总页数 注意不是总条数
		numberOfPages : 5,
		bootstrapMajorVersion : 3,
		pageUrl : function(type, page, current) {
			if (page == current) {
				return "javascript:void(0)";
			} else {
				return url + "?startPage=" + page;
			}
		}
	}
	$("#pagintor").bootstrapPaginator(options);// $("#pagintor")Bootstrap是2.X 使用div元素，3.X使用ul元素
}
