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


# --- !Downs

DROP TABLE BOOKMARKS;