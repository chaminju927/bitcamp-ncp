// 1.태그 찾기
//2. 태그 만들기
function jQuery(selector) {
  if (selector.startsWith("<")) {
    return document.createElement(selector.substring(1, selector.length -1));
  } else {                           //<로 시작하지 않는 경우
    return document.querySelectorAll(selector);
  }
}
var $ = jQuery;