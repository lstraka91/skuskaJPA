//package sk.ness.jpa;
//
//import sk.tsystems.gamestudio.entity.Hrac;
//import sk.tsystems.gamestudio.entity.Rejting;
//import sk.tsystems.gamestudio.entity.RatingId;
//import sk.tsystems.gamestudio.entity.Skore;
//import sk.tsystems.gamestudio.services.hibernate.CommentServiceHibernateImpl;
//import sk.tsystems.gamestudio.services.hibernate.HraServiceHibernateImpl;
//import sk.tsystems.gamestudio.services.hibernate.HracServiceHibernateImpl;
//import sk.tsystems.gamestudio.services.hibernate.RejtingServiceHibernateImpl;
//import sk.tsystems.gamestudio.services.hibernate.SkoreServiceHibernateImpl;
//
//public class Test {
//
//	public static void main(String[] args) throws Exception {
//		// Student student
//		//
//		// JpaHelper.beginTransaction();
//		// JpaHelper.getEntityManager().persist();
//		// JpaHelper.commitTransaction();
//		// JpaHelper.closeAll();
//		// Student student = new Student();
//		// student.setMeno("Ander");
//		// student.setPriezvisko("Zkosic");
//		// student.setVek(55);
//		//
//		// JpaHelper.beginTransaction();
//		// EntityManager em = JpaHelper.getEntityManager();
//		//
//		// // em.persist(student);
//		// JpaHelper.commitTransaction();
//		// Query query = em
//		// .createQuery("Select s from Student s where s.meno=:meno");
//		// query.setParameter("meno", "janko");
//		// System.out.println(query.getResultList());
//		// JpaHelper.beginTransaction();
//		// student = em.find(Student.class, 2);
//		//
//		// System.out.println(student);
//		// student.setVek(99);
//		// JpaHelper.commitTransaction();
//		// // JpaHelper.closeAll();
//		// System.out.println(student);
//		// JpaHelper.closeAll();
//
//		// System.out.println(comment.findCommentForGame("ff"));
//
//		CommentServiceHibernateImpl comment = new CommentServiceHibernateImpl();
//		SkoreServiceHibernateImpl skore = new SkoreServiceHibernateImpl();
//		RejtingServiceHibernateImpl rejting = new RejtingServiceHibernateImpl();
//		HracServiceHibernateImpl hracImpl = new HracServiceHibernateImpl();
//		HraServiceHibernateImpl gameImpl= new HraServiceHibernateImpl();
//		
//		//Hra hra = new HraServiceHibernateImpl().getGameFromDB("guessNumber");
//	//	Hrac hrac = new HracServiceHibernateImpl().getHracFromDB("Valibuk");
//		Skore sk = new Skore();
//		Hrac h = new Hrac();
//		h.setName("Gejza");
////		hracImpl.insertNewHrac(h);
////		sk.setSkore(4578);
////		sk.setHra(hra);
////		sk.setHrac(hrac);
////		skore.add(sk);
//		Rejting rejt= new Rejting();
//		RatingId rid = new RatingId();
//		//rid.setHra(hra.getIdent());
//		//rid.setHrac(hrac.getId());
//////		rejt.setHra(hra);
//////		rejt.setHrac(hrac);
//		rejt.setRejting(2);
//		rejt.setRajtId(rid);
////		rejting.add(rejt);
////		
//		rejting.getAverageRatingAndCount(rejt);
//		
////		Commentos com = new Commentos();
////		com.setUserComment("hihihi");
////		com.setHra(hra);
////		com.setHrac(hrac);
//		
//		//comment.add(com);
////		for(Commentos comnt: comment.findCommentForGame("guessNumber")){
////			System.out.println(comnt.getUserComment());
////		}
//		
////		for(Skore skre: skore.findSkoreForGame("guessNumber")){
////			System.out.println(skre.getSkore());
////		}
////		
//		// com.setUserComment("Nejaky Text");
//		// com.setIdentPlayer(1);
//		// com.setIdentGame(5);
//		// //com.setGameName("Minesweeper");
//		// // com.setPlayerName("Ignac");
//		// comment.add(com);
//
//		//
//		// JpaHelper.beginTransaction();
//		// EntityManager em = JpaHelper.getEntityManager();
//		//
//		// // Create hra Entity
//		// // Hra game = new Hra();
//		// // game.setGameName("guessNumber");
//		// // em.persist(game);
//		//
//		// //create hrac
//		// Hrac hrac = new Hrac();
//		// hrac.setName("Valibuk");
//		// em.persist(hrac);
//		//
//		//
//		// // Create c1 Entity
//		// Commentos com1 = new Commentos();
//		// com1.setUserComment("halaaaliii");
//		// com1.setHra(game);
//		// com1.setHrac(hrac);
//		//
//		// // Create c2 Entity
//		// Commentos com2 = new Commentos();
//		// com2.setUserComment("fasfsdfasdfasd");
//		// com2.setHra(game);
//		// com2.setHrac(hrac);
//		// // Create c3 Entity
//		// Commentos com3 = new Commentos();
//		// com3.setUserComment("uueqaoqdasd");
//		// com3.setHra(game);
//		// com3.setHrac(hrac);
//		// // Store cmnts
//		// em.persist(com1);
//		// em.persist(com2);
//		// em.persist(com3);
//		//
//		// em.getTransaction().commit();
//		// em.close();
//
//		
//		
//		
//	}
//}
