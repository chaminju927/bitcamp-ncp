// 1.태그 찾기
// 2. 태그 만들기
// 3. append()
// 4. 리팩토링
// 5. html()
// 6. on()
// 7. appendTo
// 8. Method Chaining 
// 9. click()   
// 10. 리팩토링     
// 11. ajax()
// 12. ajax() 코드 정리
// 13. getJSON()

function jQuery(selector) {
  return new ElementBox(selector);
}
  
//10.리팩토링
function ElementBox(selector) {
  this.el = [];       //this는 new로 만든객체, 이 안에 el이라는 빈 배열을 생성

  if (selector.startsWith("<")) {
    this.el[0] = document.createElement(selector.substring(1, selector.length -1));
    
  } else {                          
    let nodeList = document.querySelectorAll(selector);
    for (let e of nodeList) {
      this.el.push(e);
    }
  }
}
//append메서드를 프로토타입에 담기
ElementBox.prototype.append = function(childBox) {  
  for (let parent of this.el) {        
    for (let child of childBox.el) {
      parent.appendChild(child.cloneNode(true));          
    }
  }   
  for (let child of childBox.el) {
     if (child.parentElement != null || child.parentElement != undefined) {
     child.parentElement.removeChild(child);
     }
    }
    return this;   
};

//appentTo메서드를 프로토타입에 담기. 엘리먼트박스로 초기화한 함수는 공통으로 사용 가능하다
ElementBox.prototype.appendTo = function(parentBox) {       
  for (let parentTag of parentBox.el) {

    for (let child of this.el) {       
      parentTag.appendChild(child.cloneNode(true));     //parenttag에 차일드 붙이기    
    }
  } 
      for (let child of this.el) {
        if (child.parentElement != null || child.parentElement != undefined) {
        child.parentElement.removeChild(child);
      }
    }
    return this;
};

//html메서드 프로토타입에 담기 //엘리먼트박스 객체가 공통 사용 가능
ElementBox.prototype.html = function(content){
  for (let e of this.el){    
    e.innerHTML = content;
  }
  return this;
};

//on()메서드 프로토타입에 담기
ElementBox.prototype.on = function(eventName, listener) {
  for (let e of this.el) {
    e.addEventListener(eventName, listener);
  }
  return this;    
};

//click()
ElementBox.prototype.click = function(handler) {
  return this.on('click', handler);
};

//jquery에 바로 ajax 적용
jQuery.ajax = function (settings) {  
  if (settings.method == undefined) settings.method = "GET";
  if (settings.async == undefined) settings.async = true;
  
  var xhr = new XMLHttpRequest(); 
  xhr.onreadystatechange = () => {
    if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          if (settings.success == undefined) {
            return;
          }
          let result;
          if (settings.dataType == "json") {
            //json string---> javascript object (deserialize)
            result = JSON.parse(xhr.responseText)
          } else {
            result = xhr.responseText;
          }
            settings.success(result);

          } else {
            if (settings.error == undefined) {
              return;
            }
            settings.error();
          }
      }
  };
  xhr.open(settings.method, settings.url, settings.async);
  xhr.send();
};

jQuery.getJSON = function(url, success) {
  jQuery.ajax({
    url : url,
    dataType : "json",
    success : success           //받아온 success함수의 주소를 success안에 저장
  });
};
var $ = jQuery;