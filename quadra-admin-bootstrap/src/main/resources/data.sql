-- Initialize 50 demo accounts (password MD5 of 123456)
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user01','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user01');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user02','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user02');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user03','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user03');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user04','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user04');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user05','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user05');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user06','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user06');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user07','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user07');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user08','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user08');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user09','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user09');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user10','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user10');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user11','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user11');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user12','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user12');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user13','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user13');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user14','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user14');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user15','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user15');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user16','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user16');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user17','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user17');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user18','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user18');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user19','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user19');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user20','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user20');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user21','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user21');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user22','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user22');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user23','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user23');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user24','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user24');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user25','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user25');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user26','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user26');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user27','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user27');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user28','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user28');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user29','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user29');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user30','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user30');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user31','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user31');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user32','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user32');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user33','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user33');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user34','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user34');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user35','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user35');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user36','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user36');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user37','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user37');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user38','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user38');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user39','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user39');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user40','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user40');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user41','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user41');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user42','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user42');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user43','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user43');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user44','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user44');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user45','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user45');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user46','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user46');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user47','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user47');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user48','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user48');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user49','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user49');
INSERT INTO tb_admin (username, password, avatar, enabled, deleted, created_at, updated_at, deleted_at)
SELECT 'user50','e10adc3949ba59abbe56e057f20f883e',NULL,1,0,NOW(),NOW(),NULL FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM tb_admin WHERE username='user50');
