-- -----------------------------------------------------
-- Table kind
-- -----------------------------------------------------
DROP TABLE IF EXISTS kind ;

CREATE TABLE IF NOT EXISTS kind (
  idkind INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (idkind));


-- -----------------------------------------------------
-- Table key
-- -----------------------------------------------------
DROP TABLE IF EXISTS key ;

CREATE TABLE IF NOT EXISTS key (
  idkey INT NOT NULL,
  openkey VARCHAR(45) NULL,
  musical VARCHAR(45) NULL,
  PRIMARY KEY (idkey));


-- -----------------------------------------------------
-- Table track
-- -----------------------------------------------------
DROP TABLE IF EXISTS track ;

CREATE TABLE IF NOT EXISTS track (
  trackId INT NOT NULL,
  name VARCHAR(200) NULL,
  artist VARCHAR(100) NULL,
  album VARCHAR(100) NULL,
  grouping VARCHAR(100) NULL,
  kind INT NULL,
  size INT NULL,
  totalTime INT NULL,
  trackNumber INT NULL,
  trackCount INT NULL,
  year INT NULL,
  dateModified TIMESTAMP NULL,
  dateAdded TIMESTAMP NULL,
  bitRate INT NULL,
  sampeRate INT NULL,
  comments VARCHAR(200) NULL,
  playCount INT NULL,
  playDate INT NULL,
  playDateUtc TIMESTAMP NULL,
  rating INT NULL,
  trackType VARCHAR(100) NULL,
  location VARCHAR(500) NULL,
  fileFolderCount INT NULL,
  libraryFolderCount INT NULL,
  key INT NULL,
  PRIMARY KEY (trackId),
  CONSTRAINT kindfk
    FOREIGN KEY (kind)
    REFERENCES kind (idkind)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT keyfk
    FOREIGN KEY (key)
    REFERENCES key (idkey)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX kindfk_idx ON track (kind ASC);

CREATE INDEX keyfk_idx ON track (key ASC);


-- -----------------------------------------------------
-- Table genre
-- -----------------------------------------------------
DROP TABLE IF EXISTS genre ;

CREATE TABLE IF NOT EXISTS genre (
  idgenre INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (idgenre));


-- -----------------------------------------------------
-- Table genretrack
-- -----------------------------------------------------
DROP TABLE IF EXISTS genretrack ;

CREATE TABLE IF NOT EXISTS genretrack (
  genre INT NOT NULL,
  track INT NOT NULL,
  PRIMARY KEY (genre, track),
  CONSTRAINT trackfk
    FOREIGN KEY (track)
    REFERENCES track (trackId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT genrefk
    FOREIGN KEY (genre)
    REFERENCES genre (idgenre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX trackfk_idx ON genretrack (track ASC);

CREATE INDEX genrefk_idx ON genretrack (genre ASC);


-- -----------------------------------------------------
-- Table input_track
-- -----------------------------------------------------
DROP TABLE IF EXISTS input_track ;

CREATE TABLE IF NOT EXISTS input_track (
  trackId INT NOT NULL,
  name VARCHAR(200) NULL,
  artist VARCHAR(100) NULL,
  album_artist VARCHAR(100) NULL,
  album VARCHAR(100) NULL,
  genre VARCHAR(200) NULL,
  grouping VARCHAR(100) NULL,
  kind VARCHAR(150) NULL,
  size INT NULL,
  total_time INT NULL,
  disc_number INT NULL,
  disc_count INT NULL,
  track_Number INT NULL,
  track_Count INT NULL,
  year INT NULL,
  bpm INT NULL,
  date_Modified TIMESTAMP NULL,
  date_Added TIMESTAMP NULL,
  bit_Rate INT NULL,
  sample_Rate INT NULL,
  comments VARCHAR(200) NULL,
  play_Count INT NULL,
  play_Date INT NULL,
  play_Date_Utc TIMESTAMP NULL,
  skip_date TIMESTAMP NULL,
  rating INT NULL,
  album_rating INT NULL,
  album_rating_computed BOOLEAN NULL,
  compilation BOOLEAN NULL,
  artwork_count INT NULL,
  persistent_id VARCHAR(100) NULL,
  explict BOOLEAN NULL,
  track_Type VARCHAR(100) NULL,
  purchased BOOLEAN NULL,
  location VARCHAR(500) NULL,
  file_Folder_Count INT NULL,
  library_Folder_Count INT NULL,
  PRIMARY KEY (trackId));

