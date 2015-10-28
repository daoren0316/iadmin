/* 通用分页程序 Author : LiJunhui jason.hz.cn@gmail.com  */

var sUrl;
var currentPage ;
var totalRecord;
var perPageNum;
var totalPage;

function pageNav(s, c, t, p)
{
	sUrl = s;
	currentPage = c;
	totalRecord = t;
	perPageNum = p;
	totalPage = Math.ceil(totalRecord / perPageNum);
	var pageNav = '';
	if (currentPage < totalPage){
		pageNext = '<a href="'+sUrl+'&page='+(currentPage+1)+'">下一页</a>';
	}
	else{
		pageNext = '下一页';
	}
	
	if (currentPage > 1){
		pagePre = '<a href="'+sUrl+'&page='+(currentPage-1)+'">上一页</a>';
	}
	else{
		pagePre = '上一页';
	}

	pageNav = '<div align="center">共['+totalPage+']页 , 当前第'+currentPage+'页 '+pagePre+' '+pageNext+' 跳到第<input id="go_pagenum" value="'+currentPage+'" type="text" size="3">页 <input onclick="goPage()" type="button" value=" GO "></div>';
	
	//pageNav = '<div align="center">共'+totalPage+'页 , 当前第'+currentPage+'页 '+pagePre+' '+pageNext+'</div>';
	document.write(pageNav);
}

function goPage()
{
	var oInput = document.getElementById("go_pagenum");
	var pageNum = oInput.value;
	if (IsNum(pageNum)){
		if (pageNum <= totalPage && pageNum > 0){
			sUrl+='&page='+pageNum;
			window.location = sUrl;
		}
		else alert('输入超出了页码范围');
	}
	else alert('输入的不是数字');
}

function IsNum(s)  //整数
{
   if(s=="null"||s=="undefined"||s.length<1)
      return false;
   if(isNaN(parseInt(s)))
      return false;
   else
   if((parseInt(s)+"").length!=s.length)
      return false;
   else
      return true;
}