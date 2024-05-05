SELECT * FROM FAQ ;

INSERT INTO FAQ
VALUES(SEQ_FAQ_NO.NEXTVAL, '#기부 가능한 품목을 알려주세요', 
		'0~14세 사계절 의류, 유아 생활용품, 가전 및 가구, 장난감 및 도서 등이 가능합니다. 현재 실사용 가능 여부가 가장 중요하며 판단이 어려운 경우 문의해주시면 답변드리고 있습니다.', 
		1, 3);

INSERT INTO FAQ
VALUES(SEQ_FAQ_NO.NEXTVAL, '#기부 불가능한 품목을 알려주세요', 
		'찢어지거나 오염으로 훼손된 물품, 어린이집/유치원/학원의 이름 등이 프린트된 가방, 생활감이 심한 가전이나 가구, 이름이 적혀있는 물건 일체 등의 기부를 받지 않고 있습니다. 마찬가지로 문의해주시면 답변드리겠습니다.', 
		1, 3);
		
COMMIT;

SELECT COUNT(*)
FROM "FAQ";

SELECT Q_NO, QUESTION, ANSWER, CATEGORY_NO
		FROM FAQ
		ORDER BY Q_NO; 
		
UPDATE FAQ 
SET ANSWER  = 
'0~14세 사계절 의류, 유아 생활용품, 가전 및 가구, 장난감 및 도서 등이 가능합니다.\n 
현재 실사용 가능 여부가 가장 중요하며 판단이 어려운 경우 문의해주시면 답변드리고 있습니다.'
WHERE Q_NO = 1; 

UPDATE FAQ 
SET ANSWER  = 
'찢어지거나 오염으로 훼손된 물품, \n
어린이집/유치원/학원의 이름 등이 프린트된 가방, \n
생활감이 심한 가전이나 가구, 이름이 적혀있는 물건 일체 등의 기부를 받지 않고 있습니다. \n
마찬가지로 문의해주시면 답변드리겠습니다.'
WHERE Q_NO = 2; 

CREATE SEQUENCE SEQ_FAQ_NO NOCACHE;

DELETE FROM FAQ 
WHERE Q_NO = 2;
DROP SEQUENCE SEQ_FAQ_NO;

ALTER TABLE FAQ 
ADD FAQ_DEL_FL CHAR(1) DEFAULT 'N'; 

ALTER TABLE FAQ 
MODIFY QUESTION NVARCHAR2(70); 