// 1.태그 찾기
// 2. 태그 만들기
// 3. append()
// 4. 리팩토링
// 5. html()
// 6. on()
// 7. appendTo
// 8. Method Chaining 
// 9. click()   
// 10. 리팩토링 => 9까지는 새 메서드 생성시 매번 따로 붙였지만
// 10부터는 ElementBox생성자의 프로토타입에 담에
// 공통으로 사용가능하게 함       

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
var $ = jQuery;