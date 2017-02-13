# --- !Ups

CREATE TABLE BOOKMARKS (
    ID integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    URL varchar(512) NOT NULL,
    DESCRIPTION varchar(1000),
    FOLDER_NAME varchar(250)
);

INSERT INTO BOOKMARKS (URL, DESCRIPTION, FOLDER_NAME) VALUES ('http://ggia.berkeley.edu/practice/meaningful_pictures', 'Greater Good link', 'GGIA');
INSERT INTO BOOKMARKS (URL, DESCRIPTION, FOLDER_NAME) VALUES ('http://www.ybrikman.com/writing/2014/03/10/the-ultimate-guide-to-getting-started/', 'Scala', 'Scala');
INSERT INTO BOOKMARKS (URL, DESCRIPTION, FOLDER_NAME) VALUES ('http://ggia.berkeley.edu/practice/finding_silver_linings', 'Greater Good link', 'GGIA');
INSERT INTO BOOKMARKS (URL, DESCRIPTION, FOLDER_NAME) VALUES ('http://stackoverflow.com/questions/tagged/playframework', 'Scala', 'Scala');
INSERT INTO BOOKMARKS (URL, DESCRIPTION, FOLDER_NAME) VALUES ('https://github.com/mbonaci/mbo-storm/wiki/Integrate-Travis-CI-with-your-GitHub-repo', 'Github CI integration', 'Open Source')
INSERT INTO BOOKMARKS (URL, DESCRIPTION, FOLDER_NAME) VALUES ('https://expatriates.stackexchange.com/questions/7695/got-a-tier-2-uk-visa-now-what/7997#7997?newreg=ec2cdd69cec145e1a9b1040c3b2579bb', 'UK tier 2 visa', 'Expat')
INSERT INTO BOOKMARKS (URL, DESCRIPTION, FOLDER_NAME) VALUES ('http://expatriates.stackexchange.com/questions/85/how-to-open-up-your-first-uk-bank-account-without-proof-of-address', 'UK Banking thread', 'Expat')
# --- !Downs

DROP TABLE BOOKMARKS;