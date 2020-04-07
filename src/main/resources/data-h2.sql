INSERT INTO geographic_location VALUES('B6E1A6BF39BF4A0FB6D6FE7434A11784', 44.2, 356.2);
INSERT INTO geographic_location VALUES('63F199884FBF4726996E203237194654', 126.4, 155.2);

INSERT INTO address(id, country, city, street, number, geographic_location_id) VALUES('F934FC8011224090AD0551AD76F85AE9', 'Romania', 'Bucharest', 'Sos Bucuresti-Targoviste', '18C', 'B6E1A6BF39BF4A0FB6D6FE7434A11784');
INSERT INTO address(id, country, city, street, number, geographic_location_id) VALUES('CE585A1777B042D38FE32A5CCA72F8FB', 'Romania', 'Bucharest', 'Calea Rahovei', '147', '63F199884FBF4726996E203237194654');

INSERT INTO category VALUES('2D1EBC5B7D2741979CF0E84451C5BBB1','Restaurant');
INSERT INTO category VALUES('9C411522E49B472B9C698B7D03115C6E','Fastfood');

INSERT INTO restaurant VALUES('C1EB195195074939AF1426F0B82398BC', FILE_READ('classpath:restaurant_logo_default.png'), 'Springtime', 'F934FC8011224090AD0551AD76F85AE9');
INSERT INTO restaurant VALUES('79773198E8174097B0E90683D7AF1088', FILE_READ('classpath:restaurant_logo_default.png'), 'Suzana Ribs and Wings', 'CE585A1777B042D38FE32A5CCA72F8FB');
INSERT INTO restaurant VALUES('B02131EBCD7841CCB238DA994BCCB740', FILE_READ('classpath:restaurant_logo_default.png'), 'KFC', 'F934FC8011224090AD0551AD76F85AE9');

INSERT INTO restaurant_category VALUES('C1EB195195074939AF1426F0B82398BC', '9C411522E49B472B9C698B7D03115C6E');
INSERT INTO restaurant_category VALUES('79773198E8174097B0E90683D7AF1088', '2D1EBC5B7D2741979CF0E84451C5BBB1');

INSERT INTO user VALUES('C636AC426E4C49948217AAA660C7D826', 'admin@app.com', 'Admin', 'Administrator', '$2a$10$Bpe5Zk4vdJzLvU2cwzmfmeLR9eAy3Xt9iOaGhHyPaCTbhsZtjm0Ee', FILE_READ('classpath:profile_picture_default.png'), 'Admin');
INSERT INTO user VALUES('4D8E971330FA4C5EA331FAD0C262B875', 'testuser1@app.com', 'Test', 'User1', '$2a$10$Bpe5Zk4vdJzLvU2cwzmfmeLR9eAy3Xt9iOaGhHyPaCTbhsZtjm0Ee', FILE_READ('classpath:profile_picture_default.png'), 'User');
INSERT INTO user VALUES('FF8C7D5F8CFF47C5B9A2F0C8AB07CA11', 'testuser2@app.com', 'Test', 'User2', '$2a$10$Bpe5Zk4vdJzLvU2cwzmfmeLR9eAy3Xt9iOaGhHyPaCTbhsZtjm0Ee', FILE_READ('classpath:profile_picture_default.png'), 'User');
INSERT INTO user VALUES('A290538CC3684A2686289D8ED1CB174D', 'testuser3@app.com', 'Test', 'User3', '$2a$10$Bpe5Zk4vdJzLvU2cwzmfmeLR9eAy3Xt9iOaGhHyPaCTbhsZtjm0Ee', FILE_READ('classpath:profile_picture_default.png'), 'User');
INSERT INTO user VALUES('9D5AA841487741C28247724C453D935F', 'meh.mihai@gmail.com', 'Ene', 'Mihai', '$2a$10$UwoqjUBG1iNhvqLz/CjF..Jb9qaieZ57CQHR4Q.l6IGEQK1A1FoYq', FILE_READ('classpath:profile_picture_default.png'), 'User');

INSERT INTO refresh_token VALUES('EBAD570E217648D09C5D3020021C43F7', CURRENT_TIMESTAMP, null, 'E5F187BFAD04400E9EE3215FED3209A2', '9D5AA841487741C28247724C453D935F');
INSERT INTO refresh_token VALUES('E731B785F31740C09DAB52D8DB02CB7D', DATEADD('DAY',-1, CURRENT_DATE), CURRENT_TIMESTAMP, 'A0FB468E55EB469EBA61B63F0B60AA7C', '9D5AA841487741C28247724C453D935F');

INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('C9236FD8A5CF4A828101360DC04477C7', 0, 0, 0, 0, 2, 'BathroomQuality', '79773198E8174097B0E90683D7AF1088');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('D72C9D723EF04F5697BE6A607705E25A', 0, 0, 0, 0, 2, 'Staff', '79773198E8174097B0E90683D7AF1088');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('008C4F35E20D494EB05105111736064F', 0, 0, 0, 0, 2, 'Cleanliness', '79773198E8174097B0E90683D7AF1088');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('E48697EDA0D94A7A842593C068972A37', 0, 0, 0, 0, 2, 'DriveThru', '79773198E8174097B0E90683D7AF1088');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('B0987D8A94734CE59D2F9F28DFA86007', 0, 0, 0, 0, 2, 'DeliverySpeed', '79773198E8174097B0E90683D7AF1088');

INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('4077E9ECCB674DA5BB81796CD40DB229', 0, 0, 100, 0, 0, 'BathroomQuality', 'C1EB195195074939AF1426F0B82398BC');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('72AA194EFC2A4085AF9192C8A617BCB6', 0, 0, 100, 0, 0, 'Staff', 'C1EB195195074939AF1426F0B82398BC');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('1DC31FEA19C2457BB8B1B9057F5DCCEB', 0, 0, 100, 0, 0, 'Cleanliness', 'C1EB195195074939AF1426F0B82398BC');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('82FB544EE22146028D705939A05B46F2', 0, 0, 100, 0, 0, 'DriveThru', 'C1EB195195074939AF1426F0B82398BC');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('A6522B4E1DFF485CBEC36523E9F8345C', 0, 0, 100, 0, 0, 'DeliverySpeed', 'C1EB195195074939AF1426F0B82398BC');

INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('402AB51CDEC143F38A45E3BC1E188626', 100, 0, 0, 0, 0, 'BathroomQuality', 'B02131EBCD7841CCB238DA994BCCB740');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('731A08348C214502A6FC630C674BC939', 100, 0, 0, 0, 0, 'Staff', 'B02131EBCD7841CCB238DA994BCCB740');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('023C142790CB4C52BAE2926B75F2D877', 100, 0, 0, 0, 0, 'Cleanliness', 'B02131EBCD7841CCB238DA994BCCB740');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('261EA5DE083C4D7AA4F4FA30169511C3', 100, 0, 0, 0, 0, 'DriveThru', 'B02131EBCD7841CCB238DA994BCCB740');
INSERT INTO ratings(id, one_star_count, two_star_count, three_star_count, four_star_count, five_star_count, type, restaurant_id) VALUES('65445DA6C2104B80AB285E428499D534', 100, 0, 0, 0, 0, 'DeliverySpeed', 'B02131EBCD7841CCB238DA994BCCB740');
