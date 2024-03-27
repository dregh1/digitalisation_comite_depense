create database oma;
	alter database oma owner to dre;
drop table personnel;
drop sequence Personnel_SEQ cascade ;
create  sequence  Personnel_SEQ INCREMENT BY 1;

	create table personnel
		(
			id bigint primary key default (nextval('Personnel_SEQ')),
			nom varchar(50) ,
			prenom varchar(50) ,
			age integer
		);

alter table login add foreign key (idPersonnel) references personnel(id);

create  sequence  Login_SEQ INCREMENT BY 1;
	create table login
		(
			id bigint primary key default (nextval('Login_seq')),
			username varchar(50) not null,
			password varchar(50) not null,
			idPersonnel bigint 

		);


insert into login (username,password,idPersonnel) values ('rabe@gmail.com','rabe123',3) ;
	
drop table session_cd cascade;
drop table direction cascade;
	
	
--- CDG 
	-- creation session
		--session	
		create sequence  session_cd_seq INCREMENT BY 1;

		create table session_cd
				(
					id bigint primary key default nextval('session_cd_seq'),
					ref varchar(11) not null ,
					date_cloture timestamp not null,				
					is_deleted boolean default false,
					id_direction bigint,
					taux_eur numeric(32,3) not null,
					taux_usd numeric(32,3) not null,
					taux_gbp numeric(32,3) not null,
					taux_mga numeric(32,3) not null,
					is_closed boolean default false
				);
		
		alter table session_cd add foreign key (id_direction) references direction(id);

        insert into session_cd (ref,date_cloture,taux_eur,taux_gbp,taux_usd,taux_mga) values('CD-24022024','2024-02-24',4000.5,5000.25,6000.2,1500.2);

		create sequence devise_seq INCREMENT BY 1;
		create table devise
				(
					id bigint primary key default nextval ('devise_seq'),
					designation varchar(3) not null
				);

drop table demande cascade;
		create sequence demande_seq INCREMENT BY 1;
		create table demande
				(
					id bigint primary key default nextval('demande_seq'),
					id_titre_depense bigint,
					motif text not null,
					id_fournisseur bigint,
					montant_ht decimal(32,3) not null,
					is_regularisation boolean default false not null,
					id_reference bigint ,
					nom_reference varchar(50),
					id_periode bigint,
					coms_prescripteur text,
					id_devise bigint,
					id_direction bigint,
					id_etat_final bigint,
					is_valdby_ach boolean default false,
					is_valdby_pres boolean default false,
					is_valdby_cdg boolean default false,

					is_deleted boolean default false

				);



insert into demande (motif,id_fournisseur,montant_ht,id_periode,coms_prescripteur,id_devise,id_direction,is_valdby_ach,is_valdby_cdg,is_valdby_pres)
	values
	('gouter',1,500000,1,'tous est ok',1,1,false,false,false) --brouillon
	,('Equipement',1,1500000,1,'tous est ok',1,1,false,false,true) --active
	,('Equipement',1,1900000,1,'tous est ok',1,1,false,false,true); --active









		-- ALTER 

				-- ACHAT 

		alter table demande add foreign key (id_titre_depense) references titre_depense(id);
		alter table demande add foreign key (id_periode) references periode_dmd(id);
		alter table demande add foreign key (id_fournisseur) references fournisseur(id);
		alter table demande add foreign key (id_devise) references devise(id);
		alter table demande add foreign key (id_direction) references direction(id);
		alter table demande add foreign key (id_etat_final) references etat_final(id);
		alter table demande add foreign key (id_reference) references reference(id);


				-- AVIS_CDG

		alter table avis_cdg add foreign key (id_rubrique) references rubrique(id);
		alter table avis_cdg add foreign key(id_demande) references demande(id);


		-- alter table demande add foreign key (id_rubrique) references rubrique(id);


create sequence avis_achat_seq INCREMENT by 1;
	create table avis_achat
				(
					id bigint primary key default nextval ('avis_achat_seq') ,
					id_demande bigint,
					commentaire text,
					daty timestamp default currenttimestamp()
				);

	alter table avis_achat add foreign key (id_demande) references demande(id);

create sequence avis_cdg_req INCREMENT by 1;
	create table avis_cdg
				(
					id bigint primary key default nextval ('avis_cdg_req') ,
					id_demande bigint,
					commentaire text,
					id_rubrique bigint,
					sousrubrique varchar(60),
					montant_budget_mensuel decimal(32,3) default 0,
					montant_engage decimal(32,3) default 0
					);




create sequence etat_final_req INCREMENT by 1;
	create table etat_final 
		(
			id bigint primary key default nextval ('etat_final_req'),
			designation varchar(20) 
			);

		insert into etat_final(designation) values ('OK'),('NOK'),('En attente');

		-- rubrique
		create sequence rubrique_seq INCREMENT by 1;
		create table rubrique 
				(
					id bigint primary key default nextval('rubrique_seq'),
					designation varchar(50)
				);
		-- sousrubrique
		create sequence sousrubrique_seq INCREMENT by 1;
		create table sousrubrique 
				(
					id bigint primary key default nextval('sousrubrique_seq'),
					id_rubrique bigint,
					designation varchar(50) not null
				);

		create sequence direction_seq INCREMENT by 1;
		create table direction 
				(
					id bigint primary key default nextval('direction_seq'),
					designation varchar(50)

				);

		create sequence titre_depense_seq INCREMENT by 1;
		create table titre_depense 
				(
					id bigint primary key default nextval('titre_depense_seq'),
					designation varchar(50)

				);


		-- insertion
		insert into devise (designation) values ('EUR'),('USD'),('GBP'),('MGA');
		insert into direction (designation) values ('DTI'),('ODC');

			-- create table taux_devis_session
			-- 	(
			-- 		id_session bigint,
			-- 		id_devise bigint,
			-- 		valeur decimal(20,3)
			-- 	);

			-- 	alter table taux_devis_session add foreign key (id_session) references session_cd(id);
			-- 	alter table taux_devis_session add foreign key (id_devise) references devise(id);

		-- A REVISER
		-- alter table session_cd alter column id_direction set default 1;

		create sequence periode_dmd_seq INCREMENT by 1;
		create table periode_dmd
		(
			id bigint primary key default nextval('periode_dmd_seq'),
			designation varchar(20) not null 
		);
		insert into periode_dmd(designation) values ('mois'),('trimestre'),('semestre'),('année');


		--
		create sequence fournisseur_seq INCREMENT by 1; 
		create table fournisseur 
		(
			id bigint primary key default nextval('fournisseur_seq'),
			nom varchar(80)
		);

		insert into fournisseur(nom) values ('Socobis'),('Chocolat Robert');

		insert into rubrique(designation) values('achat nourrire');

        insert into titre_depense (designation) values ("Team Building");



		-- VIEW
		create or replace view brouillon as 
			(

				select 
					dm.id as id,
					
					dm.id_titre_depense as id_titre,
					coalesce(td.designation, 'sans titre')  as titre,
					dm.motif as motif,
					dm.montant_ht as montant_ht,
					
					dm.is_regularisation as is_regularisation,
					
					dm.coms_prescripteur as coms_prescripteur,
					-- dm.id_etat_final as id_etat_final,
					
					
					dm.id_periode as id_periode,
					p.designation as periode,

					-- ef.designation as etat_final,

					dm.id_direction as id_direction,
					dr.designation as direction,

					dm.id_devise as id_devise,
					dv.designation as devise,

					f.id as id_fournisseur,
					f.nom as fournisseur
				from demande dm join direction dr on dm.id_direction= dr.id
								join devise dv on dm.id_devise = dv.id
								join periode_dmd p on dm.id_periode =p.id 
								join fournisseur f on dm.id_fournisseur = f.id
								full join titre_depense td on dm.id_titre_depense = td.id
				where is_valdby_pres = false and dm.is_valdby_ach=false and dm.is_valdby_cdg=false
				group by id_titre,dm.id , dr.designation, dv.designation, p.designation,f.id,td.id


				);


		create sequence demande_et_session_seq INCREMENT by 1;	
		create  table demande_et_session 
			(
				id bigint primary key default nextval ('demande_et_session_seq'),
				id_session bigint not null,
				id_demande bigint not null
			);

		create sequence reference_seq INCREMENT by 1;	

		create table reference 
			(
				id bigint primary key default nextval('reference_seq'),
				designation varchar(50)
			 );



----------------------------------------- 
		create or replace view active_dmd as 
			(

				select 
					dm.id as id,
					
					dm.id_titre_depense as id_titre,
					coalesce(td.designation, 'sans titre')  as titre,
					dm.motif as motif,
					dm.montant_ht as montant_ht,
					
					dm.is_regularisation as is_regularisation,
					
					dm.coms_prescripteur as coms_prescripteur,
					-- dm.id_etat_final as id_etat_final,
					
					
					dm.id_periode as id_periode,
					p.designation as periode,

					-- ef.designation as etat_final,

					dm.id_direction as id_direction,
					dr.designation as direction,

					dm.id_devise as id_devise,
					dv.designation as devise,



					f.id as id_fournisseur,
					f.nom as fournisseur,
					ds.id_session as id_session,
					s.is_closed as etat_session

				from demande dm join direction dr on dm.id_direction= dr.id
								join demande_et_session ds on dm.id = ds.id_demande
								join session_cd s on s.id = ds.id_session
								join devise dv on dm.id_devise = dv.id

								join periode_dmd p on dm.id_periode =p.id 
								join fournisseur f on dm.id_fournisseur = f.id
								full join titre_depense td on dm.id_titre_depense = td.id
				where is_valdby_pres = true and s.is_closed = false
				group by id_titre,dm.id , dr.designation, dv.designation, p.designation,f.id,td.id,ds.id,s.is_closed


				);


			insert into demande_et_session (id_demande,id_session) values (151,3351) , (351,3351);