// 1.태그 찾기
// 2. 태그 만들기
// 3. append()
// 4. 리팩토링
// 5. html()
// 6. on()
// 7. appendTo
// 8. Method Chaining ==> 설정한 함수의 주소를 리턴해 둔다
//                  그래야 메서드가 여러번 호출되지 않고 이어서 실행됨
function jQuery(selector) {
  let el = [];       //생성한 태그나 찾은 태그를 담는 배열

  if (selector.startsWith("<")) {
    el[0] = document.createElement(selector.substring(1, selector.length -1));
  } else {                          
    let nodeList = document.querySelectorAll(selector);
    for (let e of nodeList) {
      el.push(e);
    }
  }
  //append
  el.append = function(childBox) {       //child가 들은 '박스'를 호출.
    //자식 태그를 복제해서 각 부모 태그에 붙인다.
    for (let parent of el) {

      //자식들이 들어있는 상자에서 자식을 하나씩 꺼내 복제하여 각 부모의 자식으로 붙인다.
      for (let child of childBox) {
        parent.appendChild(child.cloneNode(true));          
      }
    } 
        //자식 태그는 제거한다.(box에서 꺼낸 후!)
        for (let child of childBox) {
          if (child.parentElement != null || child.parentElement != undefined) {
          child.parentElement.removeChild(child);
        }
      }
      return this;   //this(이 메서드가 소속된 객체) 주소를 리턴해라/
  };

//appendTo
  el.appendTo = function(parents) {       
    //자식 태그를 복제해서 각 부모 태그에 붙인다.
    for (let parent of parents) {

      for (let child of el) {        //여기서 el은 자식태그가 들은 박스.
        parent.appendChild(child.cloneNode(true));          
      }
    } 
        //자식 태그는 제거한다.(box에서 꺼낸 후!)
        for (let child of el) {
          if (child.parentElement != null || child.parentElement != undefined) {
          child.parentElement.removeChild(child);
        }
      }
      return this;
  };

//html
  el.html = function(content){
    for (let e of el){            //el목록에서 e를 꺼냄.
      e.innerHTML = content;
    }
    return this;
  };

//on()
  el.on = function(eventName, listener){
    for (let e of el){
      e.addEventListener(eventName, listener);
    }
    return this;
  };
  return el;        
}
var $ = jQuery;