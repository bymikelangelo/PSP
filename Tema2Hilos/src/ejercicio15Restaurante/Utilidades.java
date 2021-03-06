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

		String letraAzar[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","?","O","P","Q","R","S",
				"T","U","V","W","X","Y","Z"};

		return letraAzar[azar.nextInt(letraAzar.length)];
	}


	static public String a_nombreAzar() {
		Random azar= new Random();

		String nombreAzar[]={"Santiago","Sebasti?n","Diego","Thiago","Nicol?s","Samuel","Dario","Alejandro","Mateo",
				"?ngel","Mat?as","Gabriel","Tom?s","David","Emiliano","Andr?s","Joaqu?n","Carlos","Alexander","Adri?n","Lucas",
				"Sof?a","Camila","Valentina","Isabella","Valeria","Daniela","Mariana","Sara","Victoria","Gabriela","Ximena",
				"Andrea","Natalia","M?a","Martina","Luc?a","Samantha","Mar?a","Paula","Nicole"};

		return nombreAzar[azar.nextInt(nombreAzar.length)];
	}

	static public String a_apellidoAzar(){
		Random azar= new Random();

		String apellidoAzar[]={"Abad?a","Abarca","Abell?n","Abiego","Acorella","Baldovinos","Bandr?s","Cabra","Cajal","Calasanz",
				"Samper","Latorre", "Escolano", "Cebri?n", "Clemente", "Navarro","Garc?a","Hern?ndez","Mart?nez","L?pez",
				"P?rez","Fern?ndez","Ruiz","S?nchez","Rodr?guez","G?mez","Gonz?lez","Jim?nez","Cruz","Requena","Moreno","Reyes",
				"Morillas","Cortes","Ortiz","Romero","Medialdea","Exp?sito","Raya","Sierra","Membrilla","Casado","Amezcua","Mu?oz",
				"Santiago","Delgado","Moya","Serrano","Contreras","Hidalgo","Mesa","Pozo","Soria","Vera","Var?n","Parra"};

		return apellidoAzar[azar.nextInt(apellidoAzar.length)];
	}

	static public String a_marcaAzar(){
		Random azar= new Random();
		String marcaAzar[]={"Apple","Google","Coca-Cola","Microsoft","IBM","Toyota","Samsung","GE","McDonald?s","Amazon",
				"BMW","Mercedes-Benz","Disney","Intel","Cisco","Oracle","Nike","HP","Honda","Louis Vuitton","H&M","Gillette",
				"Facebook","Pepsi","American Express","SAP","IKEA","Pampers","UPS","Zara","Budweiser","eBay","J.P. Morgan",
				"Nescaf?","HSBC","Ford","Hyundai","Canon","Herm?s","Accenture","L?Or?al","Audi","Citi","Goldman Sachs",
				"Philips","AXA","Nissan","Gucci","Danone","Nestl?","Siemens","Allianz","Colgate","Porsche","Cartier",
				"Sony","3M","Morgan Stanley","Visa","Adidas","Thomson Reuters","Discovery","Panasonic","Tiffany & Co.",
				"Starbucks","Adobe","Prada","Santander","Xerox","Caterpillar","Burberry","Kia","KFC","MasterCard",
				"Johnson & Johnson","Shell","Harley-Davidson","DHL","Sprite","Lego","John Deere","Jack Daniel?s",
				"Chevrolet","FedEx","Land Rover","Huawei","Heineken","MTV","Ralph Lauren","Johnnie Walker","Corona",
				"Smirnoff","Kleenex","Hugo Boss","PayPal","Mini","Mo?t & Chandon","Lenovo"};
		return marcaAzar[azar.nextInt(marcaAzar.length)];
	}

	static public String a_marcaCoche(){
		Random azar = new Random();
		String marcaCoche[]={"BMW","AUDI","OPEL","KIA","PORCHE","RENAULT","PEUGEOT","MERCEDES","MINI","SMART","CITROEN","FORD","SEAT","WOLSKWAGEN","VOLVO","LEXUS"};
		return marcaCoche[azar.nextInt(marcaCoche.length)];



	}

	static public String a_provinciaAzar(){
		Random azar= new Random();
		String provincia[]= {"Alava","Albacete","Alicante","Almer?a","Asturias","Avila","Badajoz","Barcelona","Burgos","C?ceres",
				"C?diz","Santander","Castell?n","Ciudad Real","C?rdoba","La Coru?a","Cuenca","Gerona","Granada","Guadalajara",
				"Guip?zcoa","Huelva","Huesca","Islas Baleares","Ja?n","Le?n","L?rida","Lugo","Madrid","M?laga","Murcia","Navarra",
				"Orense","Palencia","Las Palmas","Pontevedra","La Rioja","Salamanca","Segovia","Sevilla","Soria","Tarragona",
				"Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"};
		return provincia[azar.nextInt(provincia.length)];
	}

	static public String a_comunidadAzar(){
		Random azar= new Random();

		String comunidad[] = {"Andaluc?a", "Arag?n", "Canarias", "Cantabria", "Castilla y Le?n", "Castilla-La Mancha", "Catalu?a", "Ceuta",
				"Comunidad Valenciana", "Comunidad de Madrid", "Extremadura", "Galicia", "Islas Baleares", "La Rioja", "Melilla", "Navarra", 
				"Pa?s Vasco", "Principado de Asturias", "Regi?n de Murcia"};

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
				"Anemia de Fanconi", "Anemias","Anginas", "Anorexia nerviosa", "Apnea del Sue?o", 
				"Asma bronquial", "Aterosclerosis", "Bronquiectasias", "Bronquitis Cr?nica", 
				"Brucelosis", "Bulimia", "Calambres en los M?sculos", "C?lculos renales", 
				"Callos y Callosidades", "Cefaleas de Tensi?n", "Ci?tica", "C?lico de Ri??n", 
				"Colitis Ulcerosa", "Colon Irritable", "Coma Et?lico", "Congelaci?n", 
				"Degeneraci?n Macular del Ojo", "Depresi?n", "Deshidrataci?n", 
				"Desmayos y Mareos", "Diarrea Cl?nica", "Diarrea del Viajero", 
				"Dolor de Cabeza", "Dolor de Espalda", "Dolor de Garganta", "Dolor de O?do", 
				"Embarazo en la Adolescencia", "Enfermedad de Crohn", "Enfermedad de Parkinson", 
				"Estre?imiento", "Fisura anal", "Gangli?n", "Glositis", "Gota", "Gripe", "Hemat?mesis", 
				"Hemorroides", "Hernia Discal", "Hiperlipemias y colesterol Elevado  ", 
				"Hipertensi?n arterial", "Hipertensi?n intracraneal idiop?tica", "Hipocondria", "Hipotensi?n",
				"Hongos de la piel", "Hongos en las u?as", "Intoxicaci?n por Alcohol", "Juanetes", 
				"Laberintitis", "Labios Agrietados", "Laringitis", "Latigazo Cervical", "Lengua Geogr?fica", 
				"Mareo", "Mastoiditis", "Migra?a", "Obesidad", "Orinar por la noche", 
				"Par?lisis Facial de Bell", "Piel seca. Xerosis", "Reflujo Gastroesof?gico", "Resfriado", 
				"Ronquidos", "Rotura de T?mpano", "Sangrado Nasal", "Sangrado Rectal", "Sangre en el Esputo", 
				"Sangre en la Orina", "S?ndrome de Astenia Cr?nica", "S?ndrome de Piernas Inquietas", 
				"S?ndrome hep?tico renal", "S?ndrome nefr?tico", "Sinusitis", "Talasemia", 
				"Tendinitis. Bursitis", "Tic en el P?rpado", "Varices", "V?rtigo"};

		return enfermedad[azar.nextInt(enfermedad.length)];
	}

	static public String a_medicamentoAzar(){
		Random azar= new Random();

		String medicamento[] = {"Nolotil.", "Efferalgan.", "Gelocatil.", "Adiro 100.", "Augmentine.", 
				"Orfidal Wyeth.", "Neobrufen.", "Dianben.", "Antidiab?ticos orales.", "Termalgin.", 
				"Lexatin.", "Almax.", "Trankimazin.", "Flumil.", "Sintrom.", "Frenadol complex.", 
				"Aspirina adultos.", "Dalsy.", "Espidifen.", "Tranxilium.", "Omeprazol Ratiopharm.", 
				"Cardyl.", "Yasm?n.", "Seguril.", "Omeprazol Cinfamed.", "Flutox.", "Zarator.", "A.A.S.", 
				"Plantaben.", "Noctamid.", "Enantyum.", "Zaldiar.", "Daflon 500.", "Tromalyt.", 
				"Hidrosaluretil.", "Prevencor.", "Carduran Neo.", "Duphalac.", "Ibuprofeno Kern.", 
				"Myolastan.", "Adiro 300.", "Algidol.", "Mucosan.", "Voltar?n.", "Eutirox.", "Norvas.", 
				"Clamoxyl.", "Coropres.", "Paracetamol Kern.", "Serc.", "Idaptan.", "Lizipaina.", "Plavix.", 
				"Idalprem.", "Metformina Sandoz.", "Motilium.", "Ebastel.", "Actonel.", "Tardyferon.", 
				"Analgilasa.", "Levothroid.", "Metamizol Normon.", "Diazepan Prodes.", "Couldina.", 
				"Pantecta.", "Fortasec.", "Dogmatil.", "Fero-gradumet.", "Lorazepam Normon.", "Singulair.", 
				"Omeprazol Pensa.", "Zyloric.", "Amoxicilina Normon.", "Diane 35.", "Cozaar.", 
				"Ibuprofeno Cinfa.", "Aero-red.", "Aspirina C.", "Acfol.", "Co-Diov?n.", 
				"Lormetazepam Normon.", "Paracetamol Pharmagenus.", "Primperan.", 
				"Enalapril Ratiopharm efg.", "Xumadol.", "Aerius.", "Polaramine.", "Acovil.", "Zinnat.", 
				"Omeprazol Davur.", "Anagastra.", "Sutril.", "Antalgin.", "Fosamax.", "Furosemida Cinfa.", 
				"Tertensif Retard.", "Stilnox.", "Flatoril.", "Monurol.", "Airtal."};

		return medicamento[azar.nextInt(medicamento.length)];
	}

	static public String a_ingredientesAzar() {
		Random azar= new Random();

		String IngredienteAzar[]={"huevos","patatas","macarrones","spaguetti","judias verdes","chorizo","jamon","coliflor","brocoli",
				"cebolla","calabacin","aceitunas","panceta","pimiento","chami?ones","tofu","bacalao","merluza","salmon","gazpacho","pepino",
				"atun","tomate","remolacha","pollo","ternera","ternasco","leche","patatas a lo pobre","lechuga","albondigas","jamon york",
				"sanjacobos","salchichas","arroz","platano","pi?a","pizza","oregano","tomillo","guisantes"};

		return IngredienteAzar[azar.nextInt(IngredienteAzar.length)];
	}

}