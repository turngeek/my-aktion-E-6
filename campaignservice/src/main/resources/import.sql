insert into Campaign (id, iban, accountname, nameofbank, donationminimum,name,targetamount,organizername) VALUES (1,'DE44123456780100200300','Max Mustermann','ABC Bank',25,'Trikots für A-Jugend', 1000.0,'max@mustermann.de');
insert into Campaign (id, iban, accountname, nameofbank, donationminimum,name,targetamount,organizername) VALUES (2,'DE44123456780100200300','Max Mustermann','ABC Bank',25,'Rollstuhl für Maria', 2500.0,'martha@mustermann.de');
insert into Donation (id, iban, name, nameofbank, amount, donorname, receiptrequested, status, campaign_id) values (100,'DE44876543210000123456','Heinz Schmidt','XXX Bank',20,'Heinz Schmidt', TRUE, 0, 1);
insert into Donation (id, iban, name, nameofbank, amount, donorname, receiptrequested, status, campaign_id) values (101,'DE44876543210000123456','Heinz Schmidt','XXX Bank',20,'Heinz Schmidt', TRUE, 0, 2);
insert into Donation (id, iban, name, nameofbank, amount, donorname, receiptrequested, status, campaign_id) values (102,'E44864275310000654321','Karl Meier','YYY Bank',30,'Karl Meier', FALSE, 1, 1);
insert into Donation (id, iban, name, nameofbank, amount, donorname, receiptrequested, status, campaign_id) values (103,'E44864275310000654321','Karl Meier','YYY Bank',30,'Karl Meier', FALSE, 1, 2);

