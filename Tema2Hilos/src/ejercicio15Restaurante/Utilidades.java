package ejercicio15Restaurante;

import java.util.Random;


public class Utilidades {

	public Utilidades() {
		// TODO Auto-generated constructor stub
	}


	/////////////////////////////////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	///////////CREAR VALORES AL AZAR/////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	static public String a_letraAzar() {
		Random azar= new Random();

		String letraAzar[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","ó","O","P","Q","R","S",
				"T","U","V","W","X","Y","Z"};

		return letraAzar[azar.nextInt(letraAzar.length)];
	}


	static public String a_nombreAzar() {
		Random azar= new Random();

		String nombreAzar[]={"Santiago","Sebastión","Diego","Thiago","Nicolós","Samuel","Dario","Alejandro","Mateo",
				"óngel","Matóas","Gabriel","Tomós","David","Emiliano","Andrós","Joaquón","Carlos","Alexander","Adrión","Lucas",
				"Sofóa","Camila","Valentina","Isabella","Valeria","Daniela","Mariana","Sara","Victoria","Gabriela","Ximena",
				"Andrea","Natalia","Móa","Martina","Lucóa","Samantha","Maróa","Paula","Nicole"};

		return nombreAzar[azar.nextInt(nombreAzar.length)];
	}

	static public String a_apellidoAzar(){
		Random azar= new Random();

		String apellidoAzar[]={"Abadóa","Abarca","Abellón","Abiego","Acorella","Baldovinos","Bandrós","Cabra","Cajal","Calasanz",
				"Samper","Latorre", "Escolano", "Cebrión", "Clemente", "Navarro","Garcóa","Hernóndez","Martónez","López",
				"Pórez","Fernóndez","Ruiz","Sónchez","Rodróguez","Gómez","Gonzólez","Jimónez","Cruz","Requena","Moreno","Reyes",
				"Morillas","Cortes","Ortiz","Romero","Medialdea","Expósito","Raya","Sierra","Membrilla","Casado","Amezcua","Muóoz",
				"Santiago","Delgado","Moya","Serrano","Contreras","Hidalgo","Mesa","Pozo","Soria","Vera","Varón","Parra"};

		return apellidoAzar[azar.nextInt(apellidoAzar.length)];
	}

	static public String a_marcaAzar(){
		Random azar= new Random();
		String marcaAzar[]={"Apple","Google","Coca-Cola","Microsoft","IBM","Toyota","Samsung","GE","McDonaldós","Amazon",
				"BMW","Mercedes-Benz","Disney","Intel","Cisco","Oracle","Nike","HP","Honda","Louis Vuitton","H&M","Gillette",
				"Facebook","Pepsi","American Express","SAP","IKEA","Pampers","UPS","Zara","Budweiser","eBay","J.P. Morgan",
				"Nescafó","HSBC","Ford","Hyundai","Canon","Hermós","Accenture","LóOróal","Audi","Citi","Goldman Sachs",
				"Philips","AXA","Nissan","Gucci","Danone","Nestló","Siemens","Allianz","Colgate","Porsche","Cartier",
				"Sony","3M","Morgan Stanley","Visa","Adidas","Thomson Reuters","Discovery","Panasonic","Tiffany & Co.",
				"Starbucks","Adobe","Prada","Santander","Xerox","Caterpillar","Burberry","Kia","KFC","MasterCard",
				"Johnson & Johnson","Shell","Harley-Davidson","DHL","Sprite","Lego","John Deere","Jack Danielós",
				"Chevrolet","FedEx","Land Rover","Huawei","Heineken","MTV","Ralph Lauren","Johnnie Walker","Corona",
				"Smirnoff","Kleenex","Hugo Boss","PayPal","Mini","Moót & Chandon","Lenovo"};
		return marcaAzar[azar.nextInt(marcaAzar.length)];
	}

	static public String a_marcaCoche(){
		Random azar = new Random();
		String marcaCoche[]={"BMW","AUDI","OPEL","KIA","PORCHE","RENAULT","PEUGEOT","MERCEDES","MINI","SMART","CITROEN","FORD","SEAT","WOLSKWAGEN","VOLVO","LEXUS"};
		return marcaCoche[azar.nextInt(marcaCoche.length)];



	}

	static public String a_provinciaAzar(){
		Random azar= new Random();
		String provincia[]= {"Alava","Albacete","Alicante","Almeróa","Asturias","Avila","Badajoz","Barcelona","Burgos","Cóceres",
				"Cádiz","Santander","Castellón","Ciudad Real","Córdoba","La Coruóa","Cuenca","Gerona","Granada","Guadalajara",
				"Guipúzcoa","Huelva","Huesca","Islas Baleares","Jaón","León","Lórida","Lugo","Madrid","Mólaga","Murcia","Navarra",
				"Orense","Palencia","Las Palmas","Pontevedra","La Rioja","Salamanca","Segovia","Sevilla","Soria","Tarragona",
				"Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"};
		return provincia[azar.nextInt(provincia.length)];
	}

	static public String a_comunidadAzar(){
		Random azar= new Random();

		String comunidad[] = {"Andalucía", "Aragón", "Canarias", "Cantabria", "Castilla y León", "Castilla-La Mancha", "Cataluña", "Ceuta",
				"Comunidad Valenciana", "Comunidad de Madrid", "Extremadura", "Galicia", "Islas Baleares", "La Rioja", "Melilla", "Navarra", 
				"País Vasco", "Principado de Asturias", "Región de Murcia"};

		return comunidad[azar.nextInt(comunidad.length)];
	}

	static public String a_dniAzar(){
		Random azar= new Random();
		String dni;
		String letra[]={"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		int numero, calculo;
		numero=azar.nextInt((99999999-10000000)+1)+10000000;
		calculo=numero%23;
		dni=numero+letra[calculo];
		return dni;
	}

	static public boolean a_validaDni(String DNI){
		String dato = DNI;
		boolean estado=false;
		int num;
		String letra[]={"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		num = Integer.parseInt(dato.substring(0, 8));
		if(dato.substring(8, 9).toUpperCase().compareTo(letra[num%23])==0){
			estado=true;
		}else{
			//System.out.println("DNI erroneo");
			estado=false;
		}
		return estado;
	}

	static public int a_enteroAzar(int desde, int hasta){
		Random azar= new Random();
		return azar.nextInt(hasta-desde+1)+desde;
	}

	static public double a_decimalAzar(int desde, int hasta){
		Random azar= new Random();
		return (azar.nextInt((int)((hasta-desde)*100+1))+desde*100) / 100.00;
	}

	static public String a_enfermedadAzar(){
		Random azar= new Random();

		String enfermedad[] = {"Absceso dental", "Accidente Cerebro Vascular (Ictus)", "Alcoholismo", 
				"Anemia de Fanconi", "Anemias","Anginas", "Anorexia nerviosa", "Apnea del Sueóo", 
				"Asma bronquial", "Aterosclerosis", "Bronquiectasias", "Bronquitis Crónica", 
				"Brucelosis", "Bulimia", "Calambres en los Mósculos", "Cólculos renales", 
				"Callos y Callosidades", "Cefaleas de Tensión", "Ciótica", "Cólico de Rióón", 
				"Colitis Ulcerosa", "Colon Irritable", "Coma Etólico", "Congelación", 
				"Degeneración Macular del Ojo", "Depresión", "Deshidratación", 
				"Desmayos y Mareos", "Diarrea Clónica", "Diarrea del Viajero", 
				"Dolor de Cabeza", "Dolor de Espalda", "Dolor de Garganta", "Dolor de Oódo", 
				"Embarazo en la Adolescencia", "Enfermedad de Crohn", "Enfermedad de Parkinson", 
				"Estreóimiento", "Fisura anal", "Ganglión", "Glositis", "Gota", "Gripe", "Hematómesis", 
				"Hemorroides", "Hernia Discal", "Hiperlipemias y colesterol Elevado  ", 
				"Hipertensión arterial", "Hipertensión intracraneal idiopótica", "Hipocondria", "Hipotensión",
				"Hongos de la piel", "Hongos en las uóas", "Intoxicación por Alcohol", "Juanetes", 
				"Laberintitis", "Labios Agrietados", "Laringitis", "Latigazo Cervical", "Lengua Geogrófica", 
				"Mareo", "Mastoiditis", "Migraóa", "Obesidad", "Orinar por la noche", 
				"Parólisis Facial de Bell", "Piel seca. Xerosis", "Reflujo Gastroesofógico", "Resfriado", 
				"Ronquidos", "Rotura de Tómpano", "Sangrado Nasal", "Sangrado Rectal", "Sangre en el Esputo", 
				"Sangre en la Orina", "Sóndrome de Astenia Crónica", "Sóndrome de Piernas Inquietas", 
				"Sóndrome hepótico renal", "Sóndrome nefrótico", "Sinusitis", "Talasemia", 
				"Tendinitis. Bursitis", "Tic en el Pórpado", "Varices", "Vórtigo"};

		return enfermedad[azar.nextInt(enfermedad.length)];
	}

	static public String a_medicamentoAzar(){
		Random azar= new Random();

		String medicamento[] = {"Nolotil.", "Efferalgan.", "Gelocatil.", "Adiro 100.", "Augmentine.", 
				"Orfidal Wyeth.", "Neobrufen.", "Dianben.", "Antidiabóticos orales.", "Termalgin.", 
				"Lexatin.", "Almax.", "Trankimazin.", "Flumil.", "Sintrom.", "Frenadol complex.", 
				"Aspirina adultos.", "Dalsy.", "Espidifen.", "Tranxilium.", "Omeprazol Ratiopharm.", 
				"Cardyl.", "Yasmón.", "Seguril.", "Omeprazol Cinfamed.", "Flutox.", "Zarator.", "A.A.S.", 
				"Plantaben.", "Noctamid.", "Enantyum.", "Zaldiar.", "Daflon 500.", "Tromalyt.", 
				"Hidrosaluretil.", "Prevencor.", "Carduran Neo.", "Duphalac.", "Ibuprofeno Kern.", 
				"Myolastan.", "Adiro 300.", "Algidol.", "Mucosan.", "Voltarón.", "Eutirox.", "Norvas.", 
				"Clamoxyl.", "Coropres.", "Paracetamol Kern.", "Serc.", "Idaptan.", "Lizipaina.", "Plavix.", 
				"Idalprem.", "Metformina Sandoz.", "Motilium.", "Ebastel.", "Actonel.", "Tardyferon.", 
				"Analgilasa.", "Levothroid.", "Metamizol Normon.", "Diazepan Prodes.", "Couldina.", 
				"Pantecta.", "Fortasec.", "Dogmatil.", "Fero-gradumet.", "Lorazepam Normon.", "Singulair.", 
				"Omeprazol Pensa.", "Zyloric.", "Amoxicilina Normon.", "Diane 35.", "Cozaar.", 
				"Ibuprofeno Cinfa.", "Aero-red.", "Aspirina C.", "Acfol.", "Co-Diovón.", 
				"Lormetazepam Normon.", "Paracetamol Pharmagenus.", "Primperan.", 
				"Enalapril Ratiopharm efg.", "Xumadol.", "Aerius.", "Polaramine.", "Acovil.", "Zinnat.", 
				"Omeprazol Davur.", "Anagastra.", "Sutril.", "Antalgin.", "Fosamax.", "Furosemida Cinfa.", 
				"Tertensif Retard.", "Stilnox.", "Flatoril.", "Monurol.", "Airtal."};

		return medicamento[azar.nextInt(medicamento.length)];
	}

	static public String a_ingredientesAzar() {
		Random azar= new Random();

		String IngredienteAzar[]={"huevos","patatas","macarrones","spaguetti","judias verdes","chorizo","jamon","coliflor","brocoli",
				"cebolla","calabacin","aceitunas","panceta","pimiento","chamiñones","tofu","bacalao","merluza","salmon","gazpacho","pepino",
				"atun","tomate","remolacha","pollo","ternera","ternasco","leche","patatas a lo pobre","lechuga","albondigas","jamon york",
				"sanjacobos","salchichas","arroz","platano","piña","pizza","oregano","tomillo","guisantes"};

		return IngredienteAzar[azar.nextInt(IngredienteAzar.length)];
	}

}