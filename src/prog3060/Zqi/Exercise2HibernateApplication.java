package prog3060.Zqi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Exercise2HibernateApplication {
	public static void main(String[] args)
    {

		String inputAgeGroup="15 to 64 years";
		int inputCencusYear=2016;
		int inputAlternativeCode=1;
		
		
		
        Session tempSession = null;
        SessionFactory tempSessionFactory = null;
        Transaction tempTransaction = null;

        try {

/*
 * We create a SessionFactory based on the hibernate.cfg.xml
 * file; then from a SessionFactory, we can create an individual
 * Session object. The Configuration object changed significantly with
 * Hibernate Version 4; examples from older sources will not work with
 * Version 4 libraries.
 * */

// Create a Hibernate session to LeagueDB
            Configuration tempConfiguration = new Configuration();
            tempConfiguration.configure("/resources/hibernate.cfg.xml");

            tempSessionFactory = tempConfiguration.buildSessionFactory();
            tempSession = tempSessionFactory.openSession();

// Begin a transaction within the session
            tempTransaction = tempSession.beginTransaction();
// Creating a fictitious team to insert into the database
            GeographicArea tempAreaToInsert = new GeographicArea();
            tempAreaToInsert.setCode(990);
            tempAreaToInsert.setLevel(2);
            tempAreaToInsert.setName("Some random area");
            tempAreaToInsert.setAlternativeCode(10990);

// INSERT the Team object
            tempSession.save(tempAreaToInsert);
            String tempcyHQLSelectString = "SELECT cy FROM CensusYear cy Where censusYear=2016";
            CensusYear cyToInsert = (CensusYear) tempSession.createQuery(tempcyHQLSelectString).getSingleResult();
            
            String tempagHQLSelectString = "SELECT ag FROM AgeGroup ag Where ageGroupID=21";
            AgeGroup agToInsert = (AgeGroup) tempSession.createQuery(tempagHQLSelectString).getSingleResult();

 // Creating a fictitious team to insert into the database
             Age tempAgeToInsert = new Age();
             tempAgeToInsert.setAgeGroup(agToInsert);
             tempAgeToInsert.setCensusYear(cyToInsert);
             tempAgeToInsert.setGeographicArea(tempAreaToInsert);
             tempAgeToInsert.setCombined(999);
             tempAgeToInsert.setMale(555);
             tempAgeToInsert.setFemale(444);
             

 // INSERT the Team object
             tempSession.save(tempAgeToInsert);

// HQL (Hibernate Query Language) SELECT statements will return a set of Objects
// or a set of Object arrays.  This depends on how many items are being queried for.
            String tempHQLSelectString = "SELECT ag, a , cy, ga FROM AgeGroup ag "
                   + "JOIN ag.age a "
                   + "JOIN a.censusYear cy "
                   + "JOIN a.geographicArea ga "
                   + "WHERE ag.description LIKE :inputAgeGroup "
                   + "AND cy.censusYear = :inputCencusYear "
                   + "AND ga.alternativeCode = :inputAlternativeCode ";

            Query <Object[]> tempQuery = tempSession.createQuery(tempHQLSelectString);

            
// Setting maximum number of results may gbe useful in some cases for pagination
            tempQuery.setMaxResults(25);

// Retrieve the results from the database and store as a collection
            List <Object[]> tempResultList = tempQuery
            		.setParameter("inputAgeGroup", inputAgeGroup)
            		.setParameter("inputCencusYear", inputCencusYear)
            		.setParameter("inputAlternativeCode", inputAlternativeCode)
            		.getResultList();

// Use an iterator to the collection to traverse the results
            Iterator <Object[]> tempResultListIterator = tempResultList.iterator();

// Produce a simple report showing some data about the retrieved teams
            System.out.println("Begin Report.");

            List <String> tempOutputTable = new ArrayList <String>();

            tempOutputTable.add(String.format("%-20s", "Desc")
                    + String.format("%-15s", "year")
                    + String.format("%-25s", "Alt code"));


            while (tempResultListIterator.hasNext())
            {

                Object[] tempResultSet = tempResultListIterator.next();
                
                
                
                Age tempA = (Age) tempResultSet[1];
                AgeGroup tempAG = (AgeGroup) tempResultSet[0];
                CensusYear tempCY = (CensusYear) tempResultSet[2];
                GeographicArea tempGA = (GeographicArea) tempResultSet[3];


                String tempAgeGroup = tempAG.getDescription();
                int tempCensusYear = tempCY.getCensusYear();
                int tempAlternativeCode = tempGA.getAlternativeCode();

                tempOutputTable.add(String.format("%-20s", tempAgeGroup)
                        + String.format("%-15d", tempCensusYear)
                        + String.format("%-25s", tempAlternativeCode));

            }

            PrintOutput("Age - ", tempOutputTable);

            String tempaHQLSelectString = "SELECT ag, a, cy, ga FROM AgeGroup ag "
                    + "JOIN ag.age a "
                    + "JOIN a.censusYear cy "
                    + "JOIN a.geographicArea ga "
                    + "WHERE ga.alternativeCode=10990";
            Query <Object[]> tempaQuery = tempSession.createQuery(tempaHQLSelectString);
            List <Object[]> tempaResultList = tempaQuery.getResultList();
            Iterator <Object[]> tempaResultListIterator = tempaResultList.iterator();

         // Produce a simple report showing some data about the retrieved teams
                     System.out.println("Begin Report.");

                     while (tempaResultListIterator.hasNext())
                     {

                         Object[] tempaResultSet = tempaResultListIterator.next();
                         
                         
                         
                         Age tempaA = (Age) tempaResultSet[1];
                         AgeGroup tempaAG = (AgeGroup) tempaResultSet[0];
                         CensusYear tempaCY = (CensusYear) tempaResultSet[2];
                         GeographicArea tempaGA = (GeographicArea) tempaResultSet[3];


                         System.out.println(tempaCY.getCensusYear());
                         System.out.println(tempaAG.getDescription());
                         System.out.println(tempaGA.getName());
                         System.out.println(tempaGA.getCode());
                         System.out.println(tempaGA.getLevel());
                         System.out.println(tempaGA.getAlternativeCode());
                         System.out.println(tempaA.getMale());
                         System.out.println(tempaA.getFemale());
                         System.out.println(tempaA.getCombined());

                     }

            System.out.println("Report done.");

// If the transaction should be reverted or completed, use rollback() or commit(), respectively
            if (tempTransaction != null)
            {

                tempTransaction.rollback();
//              tempTransaction.commit();

            }

        }
        catch(Exception e)
        {

            if (tempTransaction != null)
            {

                tempTransaction.rollback();

            }

            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        finally
        {

// Free the resources devoted to the Hibernate session
            if (tempSession != null)
            {

                tempSession.close();

            }

            tempSessionFactory.close();

        }

    }

    private static void PrintOutput(String tempOutputTableTitle, List <String> tempOutputTable)
    {

        System.out.println("******************************************************************************************");
        System.out.println();
        System.out.println(tempOutputTableTitle);
        System.out.println();

        for (String tempOutputTableElement : tempOutputTable)
        {

            System.out.println(tempOutputTableElement);

        }

        System.out.println();
        System.out.println("******************************************************************************************");

    }


}

