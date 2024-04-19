create database oma;
	alter database oma owner to dre;

--TRUNCATE
--truncate table sessioncd cascade;
--truncate table demande cascade;
--truncate table titredepense cascade;
--truncate table fournisseur cascade;
--truncate table periode cascade;
--truncate table avisAchat cascade;
--truncate table avisCdg cascade;
--truncate table etatFinal cascade;
--truncate table rubrique cascade;
--truncate table direction cascade;
--truncate table demandeEtSession cascade;


--DROP
--drop table sessioncd cascade;
--drop table demande cascade;
--drop table titredepense cascade;
--drop table fournisseur cascade;
--drop table periode cascade;
--drop table avisAchat cascade;
--drop table avisCdg cascade;
--drop table etatFinal cascade;
--drop table rubrique cascade;
--drop table direction cascade;
--drop table demandeEtSession cascade;


-- 			--	table tsy ilaina







	
	
--- CDG 
	-- creation session

		--SESSION	

--create sequence sessionCd_seq increment by 1;
		create table sessionCd
				(
					id serial primary key,
					ref varchar(11) not null ,
					dateCloture timestamp not null,
					estSupprime boolean default false,
					idDirection bigint,
					tauxEur numeric(32,3) not null,
					tauxUsd numeric(32,3) not null,
					tauxMga numeric(32,3) not null,
					estFerme boolean default false
				);
		

        -- insert into sessioncd (ref,date_cloture,taux_eur,taux_gbp,taux_usd,taux_mga) values('CD-24022024','2024-02-24',4000.5,5000.25,6000.2,1500.2);



		-- DEMANDE
--		create sequence demande_seq increment by 1;
			create  table demande
					(
						id serial primary key ,
						idTitreDepense bigint,
						motif text not null,
						idFournisseur bigint not null,
						estRegularisation boolean default false not null,
						
						comsPrescripteur text,
						idDirection bigint,
						idPeriode bigint not null,
						
						typeReference varchar(10),
						nomReference varchar(50),
						
						typeDevise varchar(10) not null, --
						montantHt decimal(32,3) not null,
                        idRubrique bigint,
                        sousRubrique varchar(50),

						
						etatFinal varchar(10),

						validationAchat boolean default false,
						validationPrescripteur boolean default false,
						validationCdg boolean default false,

						estSupprime boolean default false

					);


--        create sequence avisAchat_seq increment by 1;
		create table avisAchat
					(
						id serial primary key ,
						idDemande bigint,
						commentaire text,
						daty timestamp default now()
					);


--		create sequence avisCdg_seq INCREMENT by 1;
		create table avisCdg
					(
						id serial primary key ,
						idDemande bigint,
						commentaire text,
						montantBudgetMensuel decimal(32,3) default 0,
						montantEngage decimal(32,3) default 0
						);


--		create sequence etatFinal_seq INCREMENT by 1;
		create table etatFinal
			(
				id serial primary key,
				designation varchar(20) 
				);


		-- rubrique
--		create sequence rubrique_seq INCREMENT by 1;
		create table rubrique 
				(
					id serial primary key,
					designation varchar(50)
				);


--		create sequence direction_seq INCREMENT by 1;
		create table direction 
				(
					id serial primary key,
					designation varchar(50)

				);
--create sequence titredepense_seq increment by 1;

		create table titreDepense
				(
					id serial primary key  ,
					idDirection integer,
					idSession integer,
					designation varchar(50)

				);


--		create sequence periode_seq INCREMENT by 1;
		create table periode
		(
			id serial primary key,
			designation varchar(20) not null 
		);


		--
--		create sequence fournisseur_seq INCREMENT by 1;
		create table fournisseur 
		(
			id serial primary key,
			nom varchar(80)
		);



		create  table demandeEtSession
			(
				id serial primary key ,
				idSession bigint not null,
				idDemande bigint not null
			);






	-- ALTER 			------------------------------------------
			alter table sessioncd add foreign key (idDirection) references direction(id);
					
			alter table demande add foreign key (idPeriode) references periode(id);
			alter table demande add foreign key (idDirection) references direction(id);
			alter table demande add foreign key (idTitreDepense) references titreDepense(id);
			alter table demande add foreign key (idFournisseur) references fournisseur(id);
			alter table demande add foreign key (idRubrique) references rubrique(id);



			alter table avisCdg add foreign key(idDemande) references demande(id);
			alter table avisAchat add foreign key (idDemande) references demande(id);

			alter table titreDepense add foreign key (idSession) references sessionCd(id);
			alter table titreDepense add foreign key (idDirection) references direction(id);




	-- INSERTION 		------------------------------------------
			insert into etatFinal(designation) values ('OK'),('NOK'),('En attente');
			insert into direction (designation) values ('DTI'),('ODC'),('DF'),('DRH');
			

			insert into fournisseur(nom) values ('Socobis'),('Chocolat Robert');
			insert into rubrique(designation) values('achat nourrire');
	        insert into titreDepense (designation) values ('Team Building');
			insert into periode(designation) values ('mois'),('trimestre'),('semestre'),('annee');


INSERT INTO demande (
    idTitreDepense,
    motif,
    idFournisseur,
    estRegularisation,
    comsPrescripteur,
    idDirection,
    idPeriode,
    typeReference,
    nomReference,
    typeDevise,
    montantHt,
    idRubrique,
    sousrubrique
)
VALUES (
    1,
    'Achat de fournitures de bureau',
    1,
    false,
    'Livraison urgente requise',
    1,
    1,
    'BC',
    'Facture A00000123',
    'EUR',
    542.75,
    1,
    'Papeterie'
);


	-- VIEW 			------------------------------------------
		-- BROUILLON
        create or replace view brouillon as
            (

					select
                    dm.id as id,

                        dm.idTitreDepense as idTitre,
                        coalesce(td.designation, 'sans titre')  as titre,
                        dm.motif as motif,
                        dm.montantHt as montantHt,
                        dm.typeReference as typeReference,
                        dm.nomReference as reference,
                        dm.estRegularisation as estRegularisation,

                        dm.comsPrescripteur as comsPrescripteur,
                    -- dm.id_etatFinal as id_etatFinal,

                        dm.idRubrique as idRubrique,
                        r.designation as nomRubrique,

                        dm.sousRubrique as sousRubrique,


                        dm.idPeriode as idPeriode,
                        p.designation as periode,

                        dm.idDirection as idDirection,

                        dm.typeDevise as devise,

                        f.id as idFournisseur,
                        f.nom as fournisseur
                from demande dm join fournisseur f on dm.idFournisseur = f.id
                                join periode p on p.id= dm.idPeriode
                                join rubrique r on r.id =  dm.idRubrique

                                full join titreDepense td on dm.idTitreDepense = td.id
                where validationPrescripteur = false
                group by idTitre,dm.id ,f.id,td.id,p.id,r.id


                );

        -- active_dmd

        -- ACTIVE
        create or replace view active as
            (

					select
                    dm.id as id,

                        dm.idTitreDepense as idTitre,
                        coalesce(td.designation, 'sans titre')  as titre,
                        dm.motif as motif,
                        dm.montantHt as montantHt,
                        dm.typeReference as typeReference,
                        dm.nomReference as reference,
                        dm.estRegularisation as estRegularisation,

                        dm.comsPrescripteur as comsPrescripteur,
                    -- dm.id_etatFinal as id_etatFinal,

                        dm.idRubrique as idRubrique,
                        r.designation as nomRubrique,

                        dm.sousRubrique as sousRubrique,


                        dm.idPeriode as idPeriode,
                        p.designation as periode,

                        dm.idDirection as idDirection,

                        dm.typeDevise as devise,

                        f.id as idFournisseur,
                        f.nom as fournisseur
                from demande dm join fournisseur f on dm.idFournisseur = f.id
                                join periode p on p.id= dm.idPeriode
                                join rubrique r on r.id =  dm.idRubrique

                                full join titreDepense td on dm.idTitreDepense = td.id
                where validationPrescripteur = TRUE
                group by idTitre,dm.id ,f.id,td.id,p.id,r.id


                );

        create or replace view detailDemande as
            (

                    select
                    dm.id as id,

                        dm.idTitreDepense as idTitre,
                        coalesce(td.designation, 'sans titre')  as titre,
                        dm.motif as motif,
                        dm.montantHt as montantHt,
                        dm.typeReference as typeReference,
                        dm.nomReference as reference,
                        dm.estRegularisation as estRegularisation,

                        dm.comsPrescripteur as comsPrescripteur,
                    -- dm.id_etatFinal as id_etatFinal,

                        dm.idRubrique as idRubrique,
                        r.designation as nomRubrique,

                        dm.sousRubrique as sousRubrique,


                        dm.idPeriode as idPeriode,
                        p.designation as periode,

                        dm.idDirection as idDirection,

                        dm.typeDevise as devise,
                        dm.validationPrescripteur,
                        dm.validationCdg,
                        dm.validationAchat,
                        f.id as idFournisseur,
                        f.nom as fournisseur
                from demande dm join fournisseur f on dm.idFournisseur = f.id
                                join periode p on p.id= dm.idPeriode
                                join rubrique r on r.id =  dm.idRubrique

                                full join titreDepense td on dm.idTitreDepense = td.id

                group by idTitre,dm.id ,f.id,td.id,p.id,r.id


                );

        --

insert into titredepense (iddirection,designation) values(1,'Kick off');



-----------------------
CREATE OR REPLACE VIEW titre AS
(
    SELECT
      id,
      idSession,
      idDirection,
      designation
--      etatSession
    FROM (
      SELECT
        td.id,
        td.idsession,
        td.idDirection,
        td.designation AS designation,
        coalesce(s.estferme, false) AS etatSession
      FROM titredepense td
      FULL JOIN sessionCd s ON s.id = td.idsession
    ) AS t
    WHERE t.etatSession =false
);
update sessionCd set estferme =true where id = 23;
-----------------------



