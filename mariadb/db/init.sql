CREATE TABLE IF NOT EXISTS `lieu`
(
    `id`              INT AUTO_INCREMENT PRIMARY KEY,
    `nom`             VARCHAR(255) NOT NULL,
    `adresse`         VARCHAR(255) NOT NULL,
    `capaciteAccueil` INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS `evenement`
(
    `id`              INT AUTO_INCREMENT PRIMARY KEY,
    `nom`             VARCHAR(255) NOT NULL,
    `dateHeureDebut`  DATETIME     NOT NULL,
    `dateHeureFin`    DATETIME     NOT NULL,
    `maxParticipants` INT          NOT NULL,
    `lieuId`          INT          NOT NULL,
    FOREIGN KEY (`lieuId`) REFERENCES `lieu` (`id`)
);

CREATE TABLE IF NOT EXISTS `membre`
(
    `id`            INT AUTO_INCREMENT PRIMARY KEY,
    `nom`           VARCHAR(255) NOT NULL,
    `prenom`        VARCHAR(255) NOT NULL,
    `dateNaissance` DATE         NOT NULL,
    `adresse`       VARCHAR(255) NOT NULL,
    `mail`          VARCHAR(255) NOT NULL,
    `motDePasse`    CHAR(64)     NOT NULL
);

CREATE TABLE IF NOT EXISTS `inscription`
(
    `id`           INT AUTO_INCREMENT PRIMARY KEY,
    `membreId`    INT NOT NULL,
    `evenementId` INT NOT NULL,
    FOREIGN KEY (membreId) REFERENCES membre (id),
    FOREIGN KEY (evenementId) REFERENCES evenement (id),
    UNIQUE KEY unique_inscription (`membreId`, `evenementId`)
);


