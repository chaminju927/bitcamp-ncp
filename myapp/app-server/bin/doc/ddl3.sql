-- 게시글 작성자 번호를 저장하는 컬럼 추가
-- 작성자 번호는 app_member 테이블의 pk 컬럼을 참조하는 fk로 만든다.
alter table app_board
  add column writer int,
  add constraint app_board_Fk foreign key (writer) references app_member(member_id);
    
