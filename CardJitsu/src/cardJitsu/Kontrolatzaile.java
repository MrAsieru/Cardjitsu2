package cardJitsu;

import java.util.ArrayList;
import java.util.Iterator;


public class Kontrolatzaile {
	
	private JokalariaLokala jokalari1;
	private JokalariaBot jokalari2;
	private EfektuMota aurrekoTxandakoEfektua;
	private Jokalaria aurrekoTxandakoIrabazlea;
	private Karta JokalariLokalaKarta;
	private Karta JokalariBotKarta;
	public static Kontrolatzaile nireKontrolatzaile;
	
	private Kontrolatzaile() 
	{
		this.jokalari1 = null;
		this.jokalari2 = null;
		this.aurrekoTxandakoEfektua = null;
		this.JokalariBotKarta = null;
		this.JokalariLokalaKarta = null;
	}
	//Necesita revision
	
	public static Kontrolatzaile getNireKontrolatzailea() 
	{
		if(nireKontrolatzaile==null) 
		{
			nireKontrolatzaile = new Kontrolatzaile();
		}
		return nireKontrolatzaile;
	} 
	//Necesita revision
	
	public void partidaBerriaHasi() 
	{
		
	}
	//
	
	private Jokalaria txandaBerria(Jokalaria pJokalaria) 
	{
		
	}
	//
	
	private boolean partidarenIrabazleaKonprobatu(Jokalaria pJokalaria) 
	{
		boolean irabaziDu = false;
		
		ArrayList<Karta> sua = new ArrayList<Karta>();
		Iterator<Karta> itrSua = sua.iterator();
		ArrayList<Karta> elurra = new ArrayList<Karta>();
		Iterator<Karta> itrElurra = sua.iterator();
		ArrayList<Karta> ura = new ArrayList<Karta>();
		Iterator<Karta> itrUra = sua.iterator();
		ArrayList<ArrayList<Karta>> elementuak = new ArrayList<ArrayList<Karta>>();
		elementuak.add(sua);
		elementuak.add(elurra);
		elementuak.add(ura);
		
		//Karta guztiak dagozkien listetan sartu
		for(int i=0;i<pJokalaria.gordetakoKartenKantitatea();i++) {
			Karta karta = pJokalaria.lortuGordetakoKartaPOSz();
			
			switch(karta.getElementua()) {
			case ElementuMota.SUA:
				sua.add(karta);
				break;
			case ElementuMota.ELURRA:
				elurra.add(karta);
				break;
			case ElementuMota.URA:
				ura.add(karta);
				break;
			}
		}
		
		//Konprobatu elementu bereko kartak
		for(int i=0;i<elementuak.size();i++) {
			ArrayList<KoloreMota> koloreak = new ArrayList<KoloreMota>();
			for(int j=0;j<elementuak.get(i).size();j++) {
				KoloreMota kKolorea = elementuak.get(i).get(j).getKolorea();
				if(!koloreak.contains(kKolorea)) {
					koloreak.add(kKolorea);
				}
			}
			if(koloreak.size() >= 3) {
				irabaziDu = true;
				break;
			}
		}
		
		//Konprobatu elementu desberdineko kartak
		if(!irabaziDu) {
			
			int s = 0;
			int e = 0;
			int u = 0;
			
			while(itrSua.hasNext() && !irabaziDu) {
				ArrayList<KoloreMota> koloreak = new ArrayList<KoloreMota>();
				KoloreMota suKolorea = itrSua.next().getKolorea();
				koloreak.add(suKolorea);
				
				while(itrElurra.hasNext() && !irabaziDu) {
					KoloreMota elKolorea = itrElurra.next().getKolorea();
					if(!koloreak.contains(elKolorea)) {
						koloreak.add(elKolorea);
						
						while(itrUra.hasNext() && !irabaziDu) {
							KoloreMota urKolorea = itrUra.next().getKolorea();
							if(!koloreak.contains(urKolorea)) {
								irabaziDu = true;								
							}
						}
					}
				}
			}
		}
		
		return irabaziDu;
	}
	//
	
	private Karta kartaBatEman(Jokalaria pJokalaria) 
	{
		int randomz = 1000;
		while(randomz>509) 
		{
			randomz = (int) Math.round(Math.random()*1000);
		}
		
		pJokalaria.gehituJolastekoKarta(KartaGuztiak.getKarta(randomz));
	}
	//
	
	private void konprobatuAurrekoEfektua() 
	{
		
	}
	//
	
	private JokalariMota kartakKonprobatu() 
	{
		//Baloreak gorde
		
		int irabazlea = -1;
		ElementuMota elementuaL = JokalariLokalaKarta.getElementua();;
		ElementuMota elementuaB = JokalariBotKarta.getElementua();
		int balioaL =  JokalariLokalaKarta.getBalioa();
		int balioaB =  JokalariBotKarta.getBalioa();
		boolean minwin = false;
		int finala = -1;
		
		//Aurreko jokaldia nork irabazi duen jakin
		
		if(this.aurrekoTxandakoIrabazlea instanceof (JokalariaLOKALA)Jokalaria) {irabazlea = 1;}else{irabazlea = 0;}
		
		//Irabazlearen arabera balioak aldatu
		
		switch(irabazlea) 
		{
		case 1:
			switch(aurrekoTxandakoEfektua) 
			{
			case BIGEHITU:
				balioaL = balioaL + 2;
				break;
			case BIKENDU:
				balioaB = balioaB-2;
				break;
			case ZENBAKIALDAKETA:
				minwin = true;
				break;
			}
			break;
		case 0:
			switch(aurrekoTxandakoEfektua) 
			{
			case BIGEHITU:
				balioaB = balioaB + 2;
				break;
			case BIKENDU:
				balioaL = balioaL-2;
				break;
			case ZENBAKIALDAKETA:
				minwin = true;
				break;
			}
			break;
		}
		
		//Nork irabazten du?
		
		if(elementuaL==elementuaB) 
		{
			if(balioaL>balioaB) 
			{
				if(!minwin) 
				{
					finala = 2;
				}
				else 
				{
					finala = 1;
				}
			}
			else if(balioaL<balioaB) 
			{
				if(!minwin) 
				{
					finala = 1;
				}
				else 
				{
					finala = 2;
				}
			}
			else if(balioaL==balioaB) 
			{
				finala = 0;
			}
		}
		else if(elementuaIrabazi(elementuaL,elementuaB)) 
		{
			finala = 2;
		}
		else if(elementuaIrabazi(elementuaB,elementuaL)) 
		{
			finala = 1;
		}

		// Imprimaketa
		KontsolaKontrolagailua.imprimatu("");
		
		//Balioak heman
		
		if(finala==1) 
		{
			return JokalariMota.BOT;
		}
		else if(finala==2) 
		{
			return JokalariMota.LOKALA;
		}
		else if(finala==0) 
		{
			return JokalariMota.BERDINKETA;
		}
	}
	//Falta print
	
	private boolean elementuaIrabazi(ElementuMota pElementua1,ElementuMota pElementua2) 
	{
		if(pElementua1==ElementuMota.SUA && pElementua2==ElementuMota.ELURRA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.SUA && pElementua2==ElementuMota.URA) 
		{
			return false;
		}
		else if(pElementua1==ElementuMota.URA && pElementua2==ElementuMota.SUA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.URA && pElementua2==ElementuMota.ELURRA) 
		{
			return false;
		}
		else if(pElementua1==ElementuMota.ELURRA && pElementua2==ElementuMota.URA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.ELURRA && pElementua2==ElementuMota.SUA) 
		{
			return false;
		}
	}
	//Hecho
	
}

