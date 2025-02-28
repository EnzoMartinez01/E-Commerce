package com.empresa.empresa.Initializer;

import java.util.Optional;

import com.empresa.empresa.Models.Authentication.Roles;
import com.empresa.empresa.Models.Cart.PaymentMethod;
import com.empresa.empresa.Models.Contact.StatusContact;
import com.empresa.empresa.Repositories.Cart.PaymentMethodRepository;
import com.empresa.empresa.Repositories.Contact.StatusContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.empresa.empresa.Models.Addresess.Country;
import com.empresa.empresa.Models.Addresess.Districts;
import com.empresa.empresa.Models.Addresess.Province;
import com.empresa.empresa.Models.Addresess.State;
import com.empresa.empresa.Repositories.Adresses.CountryRepository;
import com.empresa.empresa.Repositories.Adresses.ProvincesRepository;
import com.empresa.empresa.Repositories.Adresses.DistrictsRepository;
import com.empresa.empresa.Repositories.Adresses.StateRepository;
import com.empresa.empresa.Repositories.Authentication.RolesRepository;

@Component
public class Initializer implements CommandLineRunner{
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private ProvincesRepository provinceRepository;
    @Autowired
    private DistrictsRepository districtsRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private StatusContactRepository statusContactRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    public void run(String... args) throws Exception {
        // Inserción de datos de Paises
        inicializarCountry("Perú");

        // Inserción de datos de State
        inicializarState("Amazonas", "Perú");
        inicializarState("Áncash", "Perú");
        inicializarState("Apurímac", "Perú");
        inicializarState("Arequipa", "Perú");
        inicializarState("Ayacucho", "Perú");
        inicializarState("Cajamarca", "Perú");
        inicializarState("Callao", "Perú");
        inicializarState("Cusco", "Perú");
        inicializarState("Huancavelica", "Perú");
        inicializarState("Huánuco", "Perú");
        inicializarState("Ica", "Perú");
        inicializarState("Junín", "Perú");
        inicializarState("La Libertad", "Perú");
        inicializarState("Lambayeque", "Perú");
        inicializarState("Lima", "Perú");
        inicializarState("Loreto", "Perú");
        inicializarState("Madre de Dios", "Perú");
        inicializarState("Moquegua", "Perú");
        inicializarState("Pasco", "Perú");
        inicializarState("Piura", "Perú");
        inicializarState("Puno", "Perú");
        inicializarState("San Martín", "Perú");
        inicializarState("Tacna", "Perú");
        inicializarState("Tumbes", "Perú");
        inicializarState("Ucayali", "Perú");
        

        // Inserción de datos de Provincias
        // Amazonas
        inicializarProvince("Chachapoyas", "Amazonas");
        inicializarProvince("Bagua", "Amazonas");
        inicializarProvince("Bongará", "Amazonas");
        inicializarProvince("Condorcanqui", "Amazonas");
        inicializarProvince("Luya", "Amazonas");
        inicializarProvince("Rodríguez de Mendoza", "Amazonas");
        inicializarProvince("Utcubamba", "Amazonas");

        // Áncash
        inicializarProvince("Huaraz", "Áncash");
        inicializarProvince("Aija", "Áncash");
        inicializarProvince("Antonio Raymondi", "Áncash");
        inicializarProvince("Asunción", "Áncash");
        inicializarProvince("Bolognesi", "Áncash");
        inicializarProvince("Carhuaz", "Áncash");
        inicializarProvince("Carlos Fermín Fitzcarrald", "Áncash");
        inicializarProvince("Casma", "Áncash");
        inicializarProvince("Corongo", "Áncash");
        inicializarProvince("Huarmey", "Áncash");
        inicializarProvince("Huaylas", "Áncash");
        inicializarProvince("Mariscal Luzuriaga", "Áncash");
        inicializarProvince("Ocros", "Áncash");
        inicializarProvince("Pallasca", "Áncash");
        inicializarProvince("Pomabamba", "Áncash");
        inicializarProvince("Recuay", "Áncash");
        inicializarProvince("Santa", "Áncash");
        inicializarProvince("Sihuas", "Áncash");
        inicializarProvince("Yungay", "Áncash");

        // Apurímac
        inicializarProvince("Abancay", "Apurímac");
        inicializarProvince("Andahuaylas", "Apurímac");
        inicializarProvince("Antabamba", "Apurímac");
        inicializarProvince("Aymaraes", "Apurímac");
        inicializarProvince("Chincheros", "Apurímac");
        inicializarProvince("Cotabambas", "Apurímac");
        inicializarProvince("Grau", "Apurímac");

        // Arequipa
        inicializarProvince("Arequipa", "Arequipa");
        inicializarProvince("Camaná", "Arequipa");
        inicializarProvince("Caravelí", "Arequipa");
        inicializarProvince("Castilla", "Arequipa");
        inicializarProvince("Caylloma", "Arequipa");
        inicializarProvince("Condesuyos", "Arequipa");
        inicializarProvince("Islay", "Arequipa");
        inicializarProvince("La Unión", "Arequipa");

        // Ayacucho
        inicializarProvince("Huamanga", "Ayacucho");
        inicializarProvince("Cangallo", "Ayacucho");
        inicializarProvince("Huanca Sancos", "Ayacucho");
        inicializarProvince("Huanta", "Ayacucho");
        inicializarProvince("La Mar", "Ayacucho");
        inicializarProvince("Lucanas", "Ayacucho");
        inicializarProvince("Parinacochas", "Ayacucho");
        inicializarProvince("Páucar del Sara Sara", "Ayacucho");
        inicializarProvince("Sucre", "Ayacucho");
        inicializarProvince("Víctor Fajardo", "Ayacucho");
        inicializarProvince("Vilcas Huamán", "Ayacucho");

        // Cajamarca
        inicializarProvince("Cajamarca", "Cajamarca");
        inicializarProvince("Cajabamba", "Cajamarca");
        inicializarProvince("Celendín", "Cajamarca");
        inicializarProvince("Chota", "Cajamarca");
        inicializarProvince("Contumazá", "Cajamarca");
        inicializarProvince("Cutervo", "Cajamarca");
        inicializarProvince("Hualgayoc", "Cajamarca");
        inicializarProvince("Jaén", "Cajamarca");
        inicializarProvince("San Ignacio", "Cajamarca");
        inicializarProvince("San Marcos", "Cajamarca");
        inicializarProvince("San Miguel", "Cajamarca");
        inicializarProvince("San Pablo", "Cajamarca");
        inicializarProvince("Santa Cruz", "Cajamarca");

        // Callao
        inicializarProvince("Callao", "Callao");

        // Cusco
        inicializarProvince("Cusco", "Cusco");
        inicializarProvince("Acomayo", "Cusco");
        inicializarProvince("Anta", "Cusco");
        inicializarProvince("Calca", "Cusco");
        inicializarProvince("Canas", "Cusco");
        inicializarProvince("Canchis", "Cusco");
        inicializarProvince("Chumbivilcas", "Cusco");
        inicializarProvince("Espinar", "Cusco");
        inicializarProvince("La Convención", "Cusco");
        inicializarProvince("Paruro", "Cusco");
        inicializarProvince("Paucartambo", "Cusco");
        inicializarProvince("Quispicanchi", "Cusco");
        inicializarProvince("Urubamba", "Cusco");

        // Huancavelica
        inicializarProvince("Huancavelica", "Huancavelica");
        inicializarProvince("Acobamba", "Huancavelica");
        inicializarProvince("Angaraes", "Huancavelica");
        inicializarProvince("Castrovirreyna", "Huancavelica");
        inicializarProvince("Churcampa", "Huancavelica");
        inicializarProvince("Huaytará", "Huancavelica");
        inicializarProvince("Tayacaja", "Huancavelica");

        // Huánuco
        inicializarProvince("Huánuco", "Huánuco");
        inicializarProvince("Ambo", "Huánuco");
        inicializarProvince("Dos de Mayo", "Huánuco");
        inicializarProvince("Huacaybamba", "Huánuco");
        inicializarProvince("Huamalíes", "Huánuco");
        inicializarProvince("Leoncio Prado", "Huánuco");
        inicializarProvince("Marañón", "Huánuco");
        inicializarProvince("Pachitea", "Huánuco");
        inicializarProvince("Puerto Inca", "Huánuco");
        inicializarProvince("Lauricocha", "Huánuco");
        inicializarProvince("Yarowilca", "Huánuco");

        // Ica
        inicializarProvince("Ica", "Ica");
        inicializarProvince("Chincha", "Ica");
        inicializarProvince("Nazca", "Ica");
        inicializarProvince("Palpa", "Ica");
        inicializarProvince("Pisco", "Ica");

        // Junín
        inicializarProvince("Huancayo", "Junín");
        inicializarProvince("Concepción", "Junín");
        inicializarProvince("Chanchamayo", "Junín");
        inicializarProvince("Jauja", "Junín");
        inicializarProvince("Junín", "Junín");
        inicializarProvince("Satipo", "Junín");
        inicializarProvince("Tarma", "Junín");
        inicializarProvince("Yauli", "Junín");

        // La Libertad
        inicializarProvince("Trujillo", "La Libertad");
        inicializarProvince("Ascope", "La Libertad");
        inicializarProvince("Bolívar", "La Libertad");
        inicializarProvince("Chepén", "La Libertad");
        inicializarProvince("Gran Chimú", "La Libertad");
        inicializarProvince("Julcán", "La Libertad");
        inicializarProvince("Otuzco", "La Libertad");
        inicializarProvince("Pacasmayo", "La Libertad");
        inicializarProvince("Pataz", "La Libertad");
        inicializarProvince("Sánchez Carrión", "La Libertad");
        inicializarProvince("Santiago de Chuco", "La Libertad");
        inicializarProvince("Virú", "La Libertad");

        // Lambayeque
        inicializarProvince("Chiclayo", "Lambayeque");
        inicializarProvince("Ferreñafe", "Lambayeque");
        inicializarProvince("Lambayeque", "Lambayeque");

        // Lima
        inicializarProvince("Lima", "Lima");
        inicializarProvince("Barranca", "Lima");
        inicializarProvince("Cajatambo", "Lima");
        inicializarProvince("Canta", "Lima");
        inicializarProvince("Cañete", "Lima");
        inicializarProvince("Huaral", "Lima");
        inicializarProvince("Huarochirí", "Lima");
        inicializarProvince("Huaura", "Lima");
        inicializarProvince("Oyón", "Lima");
        inicializarProvince("Yauyos", "Lima");

        // Loreto
        inicializarProvince("Maynas", "Loreto");
        inicializarProvince("Alto Amazonas", "Loreto");
        inicializarProvince("Datem del Marañón", "Loreto");
        inicializarProvince("Loreto", "Loreto");
        inicializarProvince("Mariscal Ramón Castilla", "Loreto");
        inicializarProvince("Putumayo", "Loreto");
        inicializarProvince("Requena", "Loreto");
        inicializarProvince("Ucayali", "Loreto");

        // Madre de Dios
        inicializarProvince("Tambopata", "Madre de Dios");
        inicializarProvince("Manu", "Madre de Dios");
        inicializarProvince("Tahuamanu", "Madre de Dios");

        // Moquegua
        inicializarProvince("Mariscal Nieto", "Moquegua");
        inicializarProvince("General Sánchez Cerro", "Moquegua");
        inicializarProvince("Ilo", "Moquegua");

        // Pasco
        inicializarProvince("Pasco", "Pasco");
        inicializarProvince("Daniel Alcides Carrión", "Pasco");
        inicializarProvince("Oxapampa", "Pasco");

        // Piura
        inicializarProvince("Piura", "Piura");
        inicializarProvince("Ayabaca", "Piura");
        inicializarProvince("Huancabamba", "Piura");
        inicializarProvince("Morropón", "Piura");
        inicializarProvince("Paita", "Piura");
        inicializarProvince("Sechura", "Piura");
        inicializarProvince("Sullana", "Piura");
        inicializarProvince("Talara", "Piura");

        // Puno
        inicializarProvince("Puno", "Puno");
        inicializarProvince("Azángaro", "Puno");
        inicializarProvince("Carabaya", "Puno");
        inicializarProvince("Chucuito", "Puno");
        inicializarProvince("El Collao", "Puno");
        inicializarProvince("Huancané", "Puno");
        inicializarProvince("Lampa", "Puno");
        inicializarProvince("Melgar", "Puno");
        inicializarProvince("Moho", "Puno");
        inicializarProvince("San Antonio de Putina", "Puno");
        inicializarProvince("San Román", "Puno");
        inicializarProvince("Sandia", "Puno");
        inicializarProvince("Yunguyo", "Puno");

        // San Martín
        inicializarProvince("Moyobamba", "San Martín");
        inicializarProvince("Bellavista", "San Martín");
        inicializarProvince("El Dorado", "San Martín");
        inicializarProvince("Huallaga", "San Martín");
        inicializarProvince("Lamas", "San Martín");
        inicializarProvince("Mariscal Cáceres", "San Martín");
        inicializarProvince("Picota", "San Martín");
        inicializarProvince("Rioja", "San Martín");
        inicializarProvince("San Martín", "San Martín");
        inicializarProvince("Tocache", "San Martín");

        // Tacna
        inicializarProvince("Tacna", "Tacna");
        inicializarProvince("Candarave", "Tacna");
        inicializarProvince("Jorge Basadre", "Tacna");
        inicializarProvince("Tarata", "Tacna");

        // Tumbes
        inicializarProvince("Tumbes", "Tumbes");
        inicializarProvince("Contralmirante Villar", "Tumbes");
        inicializarProvince("Zarumilla", "Tumbes");

        // Ucayali
        inicializarProvince("Coronel Portillo", "Ucayali");
        inicializarProvince("Atalaya", "Ucayali");
        inicializarProvince("Padre Abad", "Ucayali");
        inicializarProvince("Purus", "Ucayali");

        // Inserción de datos para District (solo distritos de Lima)
			inicializarDistrict("Ancón", "Lima");
			inicializarDistrict("Ate", "Lima");
			inicializarDistrict("Barranco", "Lima");
			inicializarDistrict("Breña", "Lima");
			inicializarDistrict("Carabayllo", "Lima");
			inicializarDistrict("Cercado de Lima", "Lima");
			inicializarDistrict("Chaclacayo", "Lima");
			inicializarDistrict("Chorrillos", "Lima");
			inicializarDistrict("Cieneguilla", "Lima");
			inicializarDistrict("Comas", "Lima");
			inicializarDistrict("El Agustino", "Lima");
			inicializarDistrict("Independencia", "Lima");
			inicializarDistrict("Jesús María", "Lima");
			inicializarDistrict("La Molina", "Lima");
			inicializarDistrict("La Victoria", "Lima");
			inicializarDistrict("Lince", "Lima");
			inicializarDistrict("Los Olivos", "Lima");
			inicializarDistrict("Lurigancho-Chosica", "Lima");
			inicializarDistrict("Lurín", "Lima");
			inicializarDistrict("Magdalena del Mar", "Lima");
			inicializarDistrict("Miraflores", "Lima");
			inicializarDistrict("Pachacamac", "Lima");
			inicializarDistrict("Pucusana", "Lima");
			inicializarDistrict("Pueblo Libre", "Lima");
			inicializarDistrict("Puente Piedra", "Lima");
			inicializarDistrict("Punta Hermosa", "Lima");
			inicializarDistrict("Punta Negra", "Lima");
			inicializarDistrict("Rímac", "Lima");
			inicializarDistrict("San Bartolo", "Lima");
			inicializarDistrict("San Borja", "Lima");
			inicializarDistrict("San Isidro", "Lima");
			inicializarDistrict("San Juan de Lurigancho", "Lima");
			inicializarDistrict("San Juan de Miraflores", "Lima");
			inicializarDistrict("San Luis", "Lima");
			inicializarDistrict("San Martín de Porres", "Lima");
			inicializarDistrict("San Miguel", "Lima");
			inicializarDistrict("Santa Anita", "Lima");
			inicializarDistrict("Santa María del Mar", "Lima");
			inicializarDistrict("Santa Rosa", "Lima");
			inicializarDistrict("Santiago de Surco", "Lima");
			inicializarDistrict("Surquillo", "Lima");
			inicializarDistrict("Villa El Salvador", "Lima");
			inicializarDistrict("Villa María del Triunfo", "Lima");
/* 
			// Inserción de datos para Districts de Barranca (Lima)
			inicializarDistrict("Barranca", "Barranca");
			inicializarDistrict("Paramonga", "Barranca");
			inicializarDistrict("Pativilca", "Barranca");
			inicializarDistrict("Supe", "Barranca");
			inicializarDistrict("Supe Puerto", "Barranca");

			// Inserción de datos para Districts de Cajatambo (Lima)
			inicializarDistrict("Cajatambo", "Cajatambo");
			inicializarDistrict("Copa", "Cajatambo");
			inicializarDistrict("Gorgor", "Cajatambo");
			inicializarDistrict("Huancapón", "Cajatambo");
			inicializarDistrict("Manás", "Cajatambo");

			// Inserción de datos para Districts de Cañete (Lima)
			inicializarDistrict("Cañete", "Cañete");
			inicializarDistrict("Asia", "Cañete");
			inicializarDistrict("Calango", "Cañete");
			inicializarDistrict("Cerro Azul", "Cañete");
			inicializarDistrict("Chilca", "Cañete");
			inicializarDistrict("Coayllo", "Cañete");
			inicializarDistrict("Imperial", "Cañete");
			inicializarDistrict("Lunahuaná", "Cañete");
			inicializarDistrict("Mala", "Cañete");
			inicializarDistrict("Nuevo Imperial", "Cañete");
			inicializarDistrict("Pacarán", "Cañete");
			inicializarDistrict("Quilmaná", "Cañete");
			inicializarDistrict("San Antonio", "Cañete");
			inicializarDistrict("San Luis", "Cañete");
			inicializarDistrict("Santa Cruz de Flores", "Cañete");
			inicializarDistrict("Zuñiga", "Cañete");

			// Inserción de datos para Districts de Canta (Lima)
			inicializarDistrict("Arahuay", "Canta");
			inicializarDistrict("Canta", "Canta");
			inicializarDistrict("Huamantanga", "Canta");
			inicializarDistrict("Huaros", "Canta");
			inicializarDistrict("Lachaqui", "Canta");
			inicializarDistrict("San Buenaventura", "Canta");
			inicializarDistrict("Santa Rosa de Quives", "Canta");

			// Inserción de datos para Districts de Huaral (Lima)
			inicializarDistrict("Atavillos Alto", "Huaral");
			inicializarDistrict("Atavillos Bajo", "Huaral");
			inicializarDistrict("Aucallama", "Huaral");
			inicializarDistrict("Chancay", "Huaral");
			inicializarDistrict("Huaral", "Huaral");
			inicializarDistrict("Ihuarí", "Huaral");
			inicializarDistrict("Lampian", "Huaral");
			inicializarDistrict("Pacaraos", "Huaral");
			inicializarDistrict("San Miguel de Acos", "Huaral");
			inicializarDistrict("Santa Cruz de Andamarca", "Huaral");
			inicializarDistrict("Sumbilca", "Huaral");
			inicializarDistrict("Veintisiete de Noviembre", "Huaral");

			// Inserción de datos para Districts de Huarochirí (Lima)
			inicializarDistrict("Antioquía", "Huarochirí");
			inicializarDistrict("Callahuanca", "Huarochirí");
			inicializarDistrict("Carampoma", "Huarochirí");
			inicializarDistrict("Chicla", "Huarochirí");
			inicializarDistrict("Cuenca", "Huarochirí");
			inicializarDistrict("Huachupampa", "Huarochirí");
			inicializarDistrict("Huanza", "Huarochirí");
			inicializarDistrict("Huarochirí", "Huarochirí");
			inicializarDistrict("Lahuaytambo", "Huarochirí");
			inicializarDistrict("Langa", "Huarochirí");
			inicializarDistrict("Laraos", "Huarochirí");
			inicializarDistrict("Mariatana", "Huarochirí");
			inicializarDistrict("Matucana", "Huarochirí");
			inicializarDistrict("Ricardo Palma", "Huarochirí");
			inicializarDistrict("San Andrés de Tupicocha", "Huarochirí");
			inicializarDistrict("San Antonio", "Huarochirí");
			inicializarDistrict("San Bartolomé", "Huarochirí");
			inicializarDistrict("San Damián", "Huarochirí");
			inicializarDistrict("San Juan de Iris", "Huarochirí");
			inicializarDistrict("San Juan de Tantaranche", "Huarochirí");
			inicializarDistrict("San Lorenzo de Quinti", "Huarochirí");
			inicializarDistrict("San Mateo", "Huarochirí");
			inicializarDistrict("San Mateo de Otao", "Huarochirí");
			inicializarDistrict("San Pedro de Casta", "Huarochirí");
			inicializarDistrict("San Pedro de Huancayre", "Huarochirí");
			inicializarDistrict("Sangallaya", "Huarochirí");
			inicializarDistrict("Santa Cruz de Cocachacra", "Huarochirí");
			inicializarDistrict("Santa Eulalia", "Huarochirí");
			inicializarDistrict("Santiago de Anchucaya", "Huarochirí");
			inicializarDistrict("Santiago de Tuna", "Huarochirí");
			inicializarDistrict("Santo Domingo de Los Olleros", "Huarochirí");
			inicializarDistrict("Surco", "Huarochirí");

			// Inserción de datos para Districts de Huaura (Lima)
			inicializarDistrict("Ambar", "Huaura");
			inicializarDistrict("Caleta de Carquín", "Huaura");
			inicializarDistrict("Checras", "Huaura");
			inicializarDistrict("Hualmay", "Huaura");
			inicializarDistrict("Huaura", "Huaura");
			inicializarDistrict("Leoncio Prado", "Huaura");
			inicializarDistrict("Paccho", "Huaura");
			inicializarDistrict("Santa Leonor", "Huaura");
			inicializarDistrict("Santa María", "Huaura");
			inicializarDistrict("Sayán", "Huaura");
			inicializarDistrict("Végueta", "Huaura");

			// Inserción de datos para Districts de Oyón (Lima)
			inicializarDistrict("Andajes", "Oyón");
			inicializarDistrict("Caujul", "Oyón");
			inicializarDistrict("Cochamarca", "Oyón");
			inicializarDistrict("Naván", "Oyón");
			inicializarDistrict("Oyón", "Oyón");
			inicializarDistrict("Pachangara", "Oyón");

			// Inserción de datos para Districts de Yauyos (Lima)
			inicializarDistrict("Alis", "Yauyos");
			inicializarDistrict("Ayauca", "Yauyos");
			inicializarDistrict("Ayavirí", "Yauyos");
			inicializarDistrict("Azángaro", "Yauyos");
			inicializarDistrict("Cacra", "Yauyos");
			inicializarDistrict("Carania", "Yauyos");
			inicializarDistrict("Catahuasi", "Yauyos");
			inicializarDistrict("Chocos", "Yauyos");
			inicializarDistrict("Cochas", "Yauyos");
			inicializarDistrict("Colonia", "Yauyos");
			inicializarDistrict("Hongos", "Yauyos");
			inicializarDistrict("Huampara", "Yauyos");
			inicializarDistrict("Huancaya", "Yauyos");
			inicializarDistrict("Huangáscar", "Yauyos");
			inicializarDistrict("Huantán", "Yauyos");
			inicializarDistrict("Huañec", "Yauyos");
			inicializarDistrict("Laraos", "Yauyos");
			inicializarDistrict("Lincha", "Yauyos");
			inicializarDistrict("Madean", "Yauyos");
			inicializarDistrict("Miraflores", "Yauyos");
			inicializarDistrict("Omas", "Yauyos");
			inicializarDistrict("Putinza", "Yauyos");
			inicializarDistrict("Quinches", "Yauyos");
			inicializarDistrict("Quinocay", "Yauyos");
			inicializarDistrict("San Joaquín", "Yauyos");
			inicializarDistrict("San Pedro de Pilas", "Yauyos");
			inicializarDistrict("Tanta", "Yauyos");
			inicializarDistrict("Tauripampa", "Yauyos");
			inicializarDistrict("Tomas", "Yauyos");
			inicializarDistrict("Tupe", "Yauyos");
			inicializarDistrict("Viñac", "Yauyos");
			inicializarDistrict("Vitis", "Yauyos");

			// Inserción de datos para Districts de Ambo (Huánuco)
			inicializarDistrict("Ambo", "Ambo");
			inicializarDistrict("Cayna", "Ambo");
			inicializarDistrict("Colpas", "Ambo");
			inicializarDistrict("Conchamarca", "Ambo");
			inicializarDistrict("Huácar", "Ambo");
			inicializarDistrict("San Francisco", "Ambo");
			inicializarDistrict("San Rafael", "Ambo");
			inicializarDistrict("Tomay Kichwa", "Ambo");

			// Inserción de datos para Districts de Dos de Mayo (Huánuco)
			inicializarDistrict("La Unión", "Dos de Mayo");
			inicializarDistrict("Chuquis", "Dos de Mayo");
			inicializarDistrict("Marías", "Dos de Mayo");
			inicializarDistrict("Pachas", "Dos de Mayo");
			inicializarDistrict("Quivilla", "Dos de Mayo");
			inicializarDistrict("Ripán", "Dos de Mayo");
			inicializarDistrict("Shunqui", "Dos de Mayo");
			inicializarDistrict("Sillapata", "Dos de Mayo");
			inicializarDistrict("Yanas", "Dos de Mayo");

			// Inserción de datos para Districts de Huacaybamba (Huánuco)
			inicializarDistrict("Huacaybamba", "Huacaybamba");
			inicializarDistrict("Canchabamba", "Huacaybamba");
			inicializarDistrict("Cochabamba", "Huacaybamba");
			inicializarDistrict("Pinra", "Huacaybamba");

			// Inserción de datos para Districts de Huamalíes (Huánuco)
			inicializarDistrict("Llata", "Huamalíes");
			inicializarDistrict("Arancay", "Huamalíes");
			inicializarDistrict("Chavín de Pariarca", "Huamalíes");
			inicializarDistrict("Jacas Grande", "Huamalíes");
			inicializarDistrict("Jircan", "Huamalíes");
			inicializarDistrict("Miraflores", "Huamalíes");
			inicializarDistrict("Monzón", "Huamalíes");
			inicializarDistrict("Punchao", "Huamalíes");
			inicializarDistrict("Puños", "Huamalíes");
			inicializarDistrict("Singa", "Huamalíes");
			inicializarDistrict("Tantamayo", "Huamalíes");

			// Inserción de datos para Districts de Leoncio Prado (Huánuco)
			inicializarDistrict("Rupa-Rupa", "Leoncio Prado");
			inicializarDistrict("Daniel Alomía Robles", "Leoncio Prado");
			inicializarDistrict("Hermilio Valdizán", "Leoncio Prado");
			inicializarDistrict("José Crespo y Castillo", "Leoncio Prado");
			inicializarDistrict("Luyando", "Leoncio Prado");
			inicializarDistrict("Mariano Dámaso Beraún", "Leoncio Prado");

			// Inserción de datos para Districts de Marañón (Huánuco)
			inicializarDistrict("Huacrachuco", "Marañón");
			inicializarDistrict("Cholon", "Marañón");
			inicializarDistrict("San Buenaventura", "Marañón");

			// Inserción de datos para Districts de Pachitea (Huánuco)
			inicializarDistrict("Panao", "Pachitea");
			inicializarDistrict("Chaglla", "Pachitea");
			inicializarDistrict("Molino", "Pachitea");
			inicializarDistrict("Umari", "Pachitea");

			// Inserción de datos para Districts de Puerto Inca (Huánuco)
			inicializarDistrict("Puerto Inca", "Puerto Inca");
			inicializarDistrict("Codo del Pozuzo", "Puerto Inca");
			inicializarDistrict("Honoria", "Puerto Inca");
			inicializarDistrict("Tournavista", "Puerto Inca");
			inicializarDistrict("Yuyapichis", "Puerto Inca");

			// Inserción de datos para Districts de Lauricocha (Huánuco)
			inicializarDistrict("Jesús", "Lauricocha");
			inicializarDistrict("Baños", "Lauricocha");
			inicializarDistrict("Jivia", "Lauricocha");
			inicializarDistrict("Queropalca", "Lauricocha");
			inicializarDistrict("Rondos", "Lauricocha");
			inicializarDistrict("San Francisco de Asís", "Lauricocha");
			inicializarDistrict("San Miguel de Cauri", "Lauricocha");

			// Inserción de datos para Districts de Yarowilca (Huánuco)
			inicializarDistrict("Chavinillo", "Yarowilca");
			inicializarDistrict("Cahuac", "Yarowilca");
			inicializarDistrict("Chacabamba", "Yarowilca");
			inicializarDistrict("Aparicio Pomares", "Yarowilca");
			inicializarDistrict("Jacas Chico", "Yarowilca");
			inicializarDistrict("Obas", "Yarowilca");
			inicializarDistrict("Pampamarca", "Yarowilca");

			// Inserción de datos para Districts de Huánuco (Huánuco)
			inicializarDistrict("Huánuco", "Huánuco");
			inicializarDistrict("Amarilis", "Huánuco");
			inicializarDistrict("Chinchao", "Huánuco");
			inicializarDistrict("Churubamba", "Huánuco");
			inicializarDistrict("Margos", "Huánuco");
			inicializarDistrict("Pillco Marca", "Huánuco");
			inicializarDistrict("San Francisco de Cayrán", "Huánuco");
			inicializarDistrict("San Pedro de Chaulán", "Huánuco");
			inicializarDistrict("Santa María del Valle", "Huánuco");
			inicializarDistrict("Yarumayo", "Huánuco");
			inicializarDistrict("Yacus", "Huánuco");
			inicializarDistrict("San Pablo de Pillao", "Huánuco");

			// Inserción de datos para Districts de Concepción (Junín)
			inicializarDistrict("Aco", "Concepción");
			inicializarDistrict("Andamarca", "Concepción");
			inicializarDistrict("Chambara", "Concepción");
			inicializarDistrict("Cochas", "Concepción");
			inicializarDistrict("Comas", "Concepción");
			inicializarDistrict("Concepción", "Concepción");
			inicializarDistrict("Heroinas Toledo", "Concepción");
			inicializarDistrict("Manzanares", "Concepción");
			inicializarDistrict("Mariscal Castilla", "Concepción");
			inicializarDistrict("Matahuasi", "Concepción");
			inicializarDistrict("Mito", "Concepción");
			inicializarDistrict("Nueve de Julio", "Concepción");
			inicializarDistrict("Orcotuna", "Concepción");
			inicializarDistrict("San José de Quero", "Concepción");
			inicializarDistrict("Santa Rosa de Ocopa", "Concepción");

			// Inserción de datos para Districts de Chanchamayo (Junín)
			inicializarDistrict("Chanchamayo", "Chanchamayo");
			inicializarDistrict("Perene", "Chanchamayo");
			inicializarDistrict("Pichanaqui", "Chanchamayo");
			inicializarDistrict("San Luis de Shuaro", "Chanchamayo");
			inicializarDistrict("San Ramón", "Chanchamayo");
			inicializarDistrict("Vitoc", "Chanchamayo");

			// Inserción de datos para Districts de Chupaca (Junín)
			inicializarDistrict("Ahuac", "Chupaca");
			inicializarDistrict("Chongos Bajo", "Chupaca");
			inicializarDistrict("Chupaca", "Chupaca");
			inicializarDistrict("Huachac", "Chupaca");
			inicializarDistrict("Huamancaca Chico", "Chupaca");
			inicializarDistrict("San Juan de Iscos", "Chupaca");
			inicializarDistrict("San Juan de Jarpa", "Chupaca");
			inicializarDistrict("Tres de Diciembre", "Chupaca");
			inicializarDistrict("Yanacancha", "Chupaca");

			// Inserción de datos para Districts de Huancayo (Junín)
			inicializarDistrict("Carhuacallanga", "Huancayo");
			inicializarDistrict("Chacapampa", "Huancayo");
			inicializarDistrict("Chicche", "Huancayo");
			inicializarDistrict("Chilca", "Huancayo");
			inicializarDistrict("Chongos Alto", "Huancayo");
			inicializarDistrict("Chupuro", "Huancayo");
			inicializarDistrict("Colca", "Huancayo");
			inicializarDistrict("Cullhuas", "Huancayo");
			inicializarDistrict("El Tambo", "Huancayo");
			inicializarDistrict("Huancán", "Huancayo");
			inicializarDistrict("Huancayo", "Huancayo");
			inicializarDistrict("Hualhuas", "Huancayo");
			inicializarDistrict("Huayucachi", "Huancayo");
			inicializarDistrict("Ingenio", "Huancayo");
			inicializarDistrict("Pariahuanca", "Huancayo");
			inicializarDistrict("Pilcomayo", "Huancayo");
			inicializarDistrict("Pucará", "Huancayo");
			inicializarDistrict("Quichuay", "Huancayo");
			inicializarDistrict("Quilcas", "Huancayo");
			inicializarDistrict("San Agustín de Cajas", "Huancayo");
			inicializarDistrict("San Jerónimo de Tunán", "Huancayo");
			inicializarDistrict("San Pedro de Saño", "Huancayo");
			inicializarDistrict("Santo Domingo de Acobamba", "Huancayo");
			inicializarDistrict("Sapallanga", "Huancayo");
			inicializarDistrict("Sicaya", "Huancayo");
			inicializarDistrict("Viques", "Huancayo");

			// Inserción de datos para Districts de Jauja (Junín)
			inicializarDistrict("Acolla", "Jauja");
			inicializarDistrict("Apata", "Jauja");
			inicializarDistrict("Ataura", "Jauja");
			inicializarDistrict("Canchayllo", "Jauja");
			inicializarDistrict("Curicaca", "Jauja");
			inicializarDistrict("El Mantaro", "Jauja");
			inicializarDistrict("Huamali", "Jauja");
			inicializarDistrict("Huaripampa", "Jauja");
			inicializarDistrict("Huertas", "Jauja");
			inicializarDistrict("Janjaillo", "Jauja");
			inicializarDistrict("Jauja", "Jauja");
			inicializarDistrict("Julcán", "Jauja");
			inicializarDistrict("Leonor Ordóñez", "Jauja");
			inicializarDistrict("Llocllapampa", "Jauja");
			inicializarDistrict("Marco", "Jauja");
			inicializarDistrict("Masma", "Jauja");
			inicializarDistrict("Masma Chicche", "Jauja");
			inicializarDistrict("Molinos", "Jauja");
			inicializarDistrict("Monobamba", "Jauja");
			inicializarDistrict("Muqui", "Jauja");
			inicializarDistrict("Muquiyauyo", "Jauja");
			inicializarDistrict("Paca", "Jauja");
			inicializarDistrict("Paccha", "Jauja");
			inicializarDistrict("Pancan", "Jauja");
			inicializarDistrict("Parco", "Jauja");
			inicializarDistrict("Pomacancha", "Jauja");
			inicializarDistrict("Ricran", "Jauja");
			inicializarDistrict("San Lorenzo", "Jauja");
			inicializarDistrict("San Pedro de Chunan", "Jauja");
			inicializarDistrict("Sausa", "Jauja");
			inicializarDistrict("Sincos", "Jauja");
			inicializarDistrict("Tunan Marca", "Jauja");
			inicializarDistrict("Yauli", "Jauja");
			inicializarDistrict("Yauyos", "Jauja");

			// Inserción de datos para Districts de Junín (Junín)
			inicializarDistrict("Carhuamayo", "Junín");
			inicializarDistrict("Junín", "Junín");
			inicializarDistrict("Ondores", "Junín");
			inicializarDistrict("Ulcumayo", "Junín");

			// Inserción de datos para Districts de Satipo (Junín)
			inicializarDistrict("Coviriali", "Satipo");
			inicializarDistrict("Llaylla", "Satipo");
			inicializarDistrict("Mazamari", "Satipo");
			inicializarDistrict("Pampa Hermosa", "Satipo");
			inicializarDistrict("Pangoa", "Satipo");
			inicializarDistrict("Río Negro", "Satipo");
			inicializarDistrict("Río Tambo", "Satipo");
			inicializarDistrict("Satipo", "Satipo");

			// Inserción de datos para Districts de Tarma (Junín)
			inicializarDistrict("Acobamba", "Tarma");
			inicializarDistrict("Huaricolca", "Tarma");
			inicializarDistrict("Huasahuasi", "Tarma");
			inicializarDistrict("La Unión", "Tarma");
			inicializarDistrict("Palca", "Tarma");
			inicializarDistrict("Palcamayo", "Tarma");
			inicializarDistrict("San Pedro de Cajas", "Tarma");
			inicializarDistrict("Tarma", "Tarma");
			inicializarDistrict("Tapo", "Tarma");

			// Inserción de datos para Districts de Yauli (Junín)
			inicializarDistrict("Chacapalpa", "Yauli");
			inicializarDistrict("Huay-Huay", "Yauli");
			inicializarDistrict("La Oroya", "Yauli");
			inicializarDistrict("Marcapomacocha", "Yauli");
			inicializarDistrict("Morococha", "Yauli");
			inicializarDistrict("Paccha", "Yauli");
			inicializarDistrict("Santa Bárbara de Carhuacayán", "Yauli");
			inicializarDistrict("Santa Rosa de Sacco", "Yauli");
			inicializarDistrict("Suitucancha", "Yauli");
			inicializarDistrict("Yauli", "Yauli");

			// Inserción de datos para Districts de Aija (Áncash)
			inicializarDistrict("Aija", "Aija");
			inicializarDistrict("Cajamarquilla", "Aija");
			inicializarDistrict("Coris", "Aija");
			inicializarDistrict("Huacllán", "Aija");
			inicializarDistrict("La Merced", "Aija");
			inicializarDistrict("Santo Toribio", "Aija");

			// Inserción de datos para Districts de Antonio Raymondi (Áncash)
			inicializarDistrict("Antonio Raymondi", "Antonio Raymondi");
			inicializarDistrict("Chavín de Huántar", "Antonio Raymondi");
			inicializarDistrict("Huarín", "Antonio Raymondi");
			inicializarDistrict("Ripan", "Antonio Raymondi");
			inicializarDistrict("San Pedro de Chánis", "Antonio Raymondi");
			inicializarDistrict("San Rafael", "Antonio Raymondi");

			// Inserción de datos para Districts de Asunción (Áncash)
			inicializarDistrict("Asunción", "Asunción");
			inicializarDistrict("Cajacay", "Asunción");
			inicializarDistrict("Casma", "Asunción");
			inicializarDistrict("Huamboy", "Asunción");
			inicializarDistrict("Jangas", "Asunción");
			inicializarDistrict("La Merced", "Asunción");
			inicializarDistrict("Santo Toribio", "Asunción");

			// Inserción de datos para Districts de Bolognesi (Áncash)
			inicializarDistrict("Aquia", "Bolognesi");
			inicializarDistrict("Cochabamba", "Bolognesi");
			inicializarDistrict("Colquioc", "Bolognesi");
			inicializarDistrict("Huallanca", "Bolognesi");
			inicializarDistrict("La Primavera", "Bolognesi");
			inicializarDistrict("Mato", "Bolognesi");
			inicializarDistrict("Pampas", "Bolognesi");
			inicializarDistrict("Saca", "Bolognesi");
			inicializarDistrict("San Miguel de Corpanqui", "Bolognesi");
			inicializarDistrict("Ticllos", "Bolognesi");

			// Inserción de datos para Districts de Carhuaz (Áncash)
			inicializarDistrict("Carhuaz", "Carhuaz");
			inicializarDistrict("Acopampa", "Carhuaz");
			inicializarDistrict("Andaje", "Carhuaz");
			inicializarDistrict("Anta", "Carhuaz");
			inicializarDistrict("Marcará", "Carhuaz");
			inicializarDistrict("San Miguel de Aco", "Carhuaz");
			inicializarDistrict("Shilla", "Carhuaz");

			// Inserción de datos para Districts de Carlos Fermín Fitzcarrald (Áncash)
			inicializarDistrict("Acochaca", "Carlos Fermín Fitzcarrald");
			inicializarDistrict("Acochaca", "Carlos Fermín Fitzcarrald");
			inicializarDistrict("Chavín de Huántar", "Carlos Fermín Fitzcarrald");
			inicializarDistrict("Huarín", "Carlos Fermín Fitzcarrald");
			inicializarDistrict("Ripan", "Carlos Fermín Fitzcarrald");
			inicializarDistrict("San Pedro de Chánis", "Carlos Fermín Fitzcarrald");
			inicializarDistrict("San Rafael", "Carlos Fermín Fitzcarrald");

			// Inserción de datos para Districts de Casma (Áncash)
			inicializarDistrict("Casma", "Casma");
			inicializarDistrict("Buena Vista Alta", "Casma");
			inicializarDistrict("Comandante Noel", "Casma");
			inicializarDistrict("Punta del Agua", "Casma");
			inicializarDistrict("Santa Rosa", "Casma");

			// Inserción de datos para Districts de Corongo (Áncash)
			inicializarDistrict("Corongo", "Corongo");
			inicializarDistrict("Bolognesi", "Corongo");
			inicializarDistrict("San Juan de Corongo", "Corongo");
			inicializarDistrict("San Pedro de Chánis", "Corongo");

			// Inserción de datos para Districts de Huaraz (Áncash)
			inicializarDistrict("Huaraz", "Huaraz");
			inicializarDistrict("Independencia", "Huaraz");
			inicializarDistrict("Jangas", "Huaraz");
			inicializarDistrict("Olleros", "Huaraz");
			inicializarDistrict("Pampas", "Huaraz");
			inicializarDistrict("Pariahuanca", "Huaraz");
			inicializarDistrict("San Juan de Rontoy", "Huaraz");
			inicializarDistrict("Tarica", "Huaraz");

			// Inserción de datos para Districts de Huari (Áncash)
			inicializarDistrict("Huari", "Huari");
			inicializarDistrict("Aczo", "Huari");
			inicializarDistrict("Cajay", "Huari");
			inicializarDistrict("Colquioc", "Huari");
			inicializarDistrict("Huacachi", "Huari");
			inicializarDistrict("Huaricanga", "Huari");
			inicializarDistrict("San Marcos", "Huari");
			inicializarDistrict("San Pedro", "Huari");

			// Inserción de datos para Districts de Huarmey (Áncash)
			inicializarDistrict("Huarmey", "Huarmey");
			inicializarDistrict("Culebras", "Huarmey");
			inicializarDistrict("Coishco", "Huarmey");
			inicializarDistrict("San Bartolo", "Huarmey");

			// Inserción de datos para Districts de Huaylas (Áncash)
			inicializarDistrict("Caraz", "Huaylas");
			inicializarDistrict("Huallanca", "Huaylas");
			inicializarDistrict("Huata", "Huaylas");
			inicializarDistrict("Marcará", "Huaylas");
			inicializarDistrict("San Miguel de Aco", "Huaylas");
			inicializarDistrict("Shilla", "Huaylas");

			// Inserción de datos para Districts de Mariscal Luzuriaga (Áncash)
			inicializarDistrict("Llama", "Mariscal Luzuriaga");
			inicializarDistrict("Mariscal Luzuriaga", "Mariscal Luzuriaga");
			inicializarDistrict("Pampas", "Mariscal Luzuriaga");
			inicializarDistrict("San Juan de Rontoy", "Mariscal Luzuriaga");

			// Inserción de datos para Districts de Ocros (Áncash)
			inicializarDistrict("Ocros", "Ocros");
			inicializarDistrict("Anta", "Ocros");
			inicializarDistrict("Carhuapampa", "Ocros");
			inicializarDistrict("Chancas", "Ocros");
			inicializarDistrict("Cayhuayna", "Ocros");
			inicializarDistrict("Huacaschuque", "Ocros");
			inicializarDistrict("Pampas", "Ocros");
			inicializarDistrict("San Miguel de Acos", "Ocros");
			inicializarDistrict("Santo Toribio", "Ocros");

			// Inserción de datos para Districts de Pallasca (Áncash)
			inicializarDistrict("Pallasca", "Pallasca");
			inicializarDistrict("Bolognesi", "Pallasca");
			inicializarDistrict("Cabana", "Pallasca");
			inicializarDistrict("Santa Rosa", "Pallasca");
			inicializarDistrict("Ticllos", "Pallasca");

			// Inserción de datos para Districts de Pomabamba (Áncash)
			inicializarDistrict("Pomabamba", "Pomabamba");
			inicializarDistrict("Huayllán", "Pomabamba");
			inicializarDistrict("Pucayacu", "Pomabamba");
			inicializarDistrict("San Juan de Rontoy", "Pomabamba");
			inicializarDistrict("San Pedro de Chánis", "Pomabamba");

			// Inserción de datos para Districts de Recuay (Áncash)
			inicializarDistrict("Recuay", "Recuay");
			inicializarDistrict("Catac", "Recuay");
			inicializarDistrict("La Primavera", "Recuay");
			inicializarDistrict("Pampas", "Recuay");
			inicializarDistrict("San Juan de Rontoy", "Recuay");

			// Inserción de datos para Districts de Santa (Áncash)
			inicializarDistrict("Santa", "Santa");
			inicializarDistrict("Chimbote", "Santa");
			inicializarDistrict("Coishco", "Santa");
			inicializarDistrict("Macate", "Santa");
			inicializarDistrict("Moro", "Santa");
			inicializarDistrict("Nuevo Chimbote", "Santa");
			inicializarDistrict("San Bartolo", "Santa");
			inicializarDistrict("San Jacinto", "Santa");
			inicializarDistrict("San Pedro", "Santa");

			// Inserción de datos para Districts de Sihuas (Áncash)
			inicializarDistrict("Sihuas", "Sihuas");
			inicializarDistrict("Acobamba", "Sihuas");
			inicializarDistrict("Cajamarquilla", "Sihuas");
			inicializarDistrict("Llapo", "Sihuas");
			inicializarDistrict("San Juan de Rontoy", "Sihuas");
			inicializarDistrict("Ticllos", "Sihuas");

			// Inserción de datos para Districts de Yungay (Áncash)
			inicializarDistrict("Yungay", "Yungay");
			inicializarDistrict("Cascapara", "Yungay");
			inicializarDistrict("Cristo de los Andes", "Yungay");
			inicializarDistrict("Mancos", "Yungay");
			inicializarDistrict("Ranrahirca", "Yungay");
			inicializarDistrict("Yungay", "Yungay");

			// Inserción de datos para Districts de Arequipa (Arequipa)
			inicializarDistrict("Arequipa", "Arequipa");
			inicializarDistrict("Alto Selva Alegre", "Arequipa");
			inicializarDistrict("Cayma", "Arequipa");
			inicializarDistrict("Cerro Colorado", "Arequipa");
			inicializarDistrict("Characato", "Arequipa");
			inicializarDistrict("Chiguata", "Arequipa");
			inicializarDistrict("José Luis Bustamante y Rivero", "Arequipa");
			inicializarDistrict("La Joya", "Arequipa");
			inicializarDistrict("Miraflores", "Arequipa");
			inicializarDistrict("Mollebaya", "Arequipa");
			inicializarDistrict("Sachaca", "Arequipa");
			inicializarDistrict("San Juan de Siguas", "Arequipa");
			inicializarDistrict("San Juan de Tarucani", "Arequipa");
			inicializarDistrict("San Sebastián", "Arequipa");
			inicializarDistrict("San Simón", "Arequipa");
			inicializarDistrict("Santiago", "Arequipa");
			inicializarDistrict("Yarabamba", "Arequipa");
			inicializarDistrict("Yura", "Arequipa");

			// Inserción de datos para Districts de Camaná (Arequipa)
			inicializarDistrict("Camaná", "Camaná");
			inicializarDistrict("José María Quimper", "Camaná");
			inicializarDistrict("Mariano Nicolás Valcárcel", "Camaná");
			inicializarDistrict("Ocoña", "Camaná");
			inicializarDistrict("Quilca", "Camaná");
			inicializarDistrict("Samuel Pastor", "Camaná");
			inicializarDistrict("Vítor", "Camaná");

			// Inserción de datos para Districts de Caravelí (Arequipa)
			inicializarDistrict("Caravelí", "Caravelí");
			inicializarDistrict("Acarí", "Caravelí");
			inicializarDistrict("Atico", "Caravelí");
			inicializarDistrict("Bella Unión", "Caravelí");
			inicializarDistrict("Cahuacho", "Caravelí");
			inicializarDistrict("Chala", "Caravelí");
			inicializarDistrict("Huanuhuanu", "Caravelí");
			inicializarDistrict("Lomas", "Caravelí");
			inicializarDistrict("Ocoña", "Caravelí");
			inicializarDistrict("Quicacha", "Caravelí");

			// Inserción de datos para Districts de Castilla (Arequipa)
			inicializarDistrict("Castilla", "Castilla");
			inicializarDistrict("Aplao", "Castilla");
			inicializarDistrict("Andagua", "Castilla");
			inicializarDistrict("Andara", "Castilla");
			inicializarDistrict("Chachas", "Castilla");
			inicializarDistrict("Chilcaymarca", "Castilla");
			inicializarDistrict("Cayarani", "Castilla");
			inicializarDistrict("Yauri", "Castilla");

			// Inserción de datos para Districts de Caylloma (Arequipa)
			inicializarDistrict("Caylloma", "Caylloma");
			inicializarDistrict("Achoma", "Caylloma");
			inicializarDistrict("Cabanaconde", "Caylloma");
			inicializarDistrict("Caylloma", "Caylloma");
			inicializarDistrict("Chivay", "Caylloma");
			inicializarDistrict("Ichupampa", "Caylloma");
			inicializarDistrict("Lari", "Caylloma");
			inicializarDistrict("Maca", "Caylloma");
			inicializarDistrict("San Antonio de Chuca", "Caylloma");
			inicializarDistrict("Tuti", "Caylloma");

			// Inserción de datos para Districts de Condesuyos (Arequipa)
			inicializarDistrict("Caylloma", "Condesuyos");
			inicializarDistrict("Chichas", "Condesuyos");
			inicializarDistrict("Cayarani", "Condesuyos");
			inicializarDistrict("Chivay", "Condesuyos");
			inicializarDistrict("Iruya", "Condesuyos");
			inicializarDistrict("San Antonio de Chuca", "Condesuyos");

			// Inserción de datos para Districts de Islay (Arequipa)
			inicializarDistrict("Islay", "Islay");
			inicializarDistrict("Mollendo", "Islay");
			inicializarDistrict("Mejía", "Islay");
			inicializarDistrict("Camana", "Islay");
			inicializarDistrict("Mollendo", "Islay");
			inicializarDistrict("Ocoña", "Islay");

			// Inserción de datos para Districts de La Unión (Arequipa)
			inicializarDistrict("La Unión", "La Unión");
			inicializarDistrict("Choroga", "La Unión");
			inicializarDistrict("Cotaruse", "La Unión");
			inicializarDistrict("Huaynapata", "La Unión");
			inicializarDistrict("Puyca", "La Unión");
			inicializarDistrict("Sama", "La Unión");
			inicializarDistrict("Sivira", "La Unión");
			inicializarDistrict("Vilavilani", "La Unión");

			// Inserción de datos para Districts de Cangallo (Ayacucho)
			inicializarDistrict("Cangallo", "Cangallo");
			inicializarDistrict("Chuschi", "Cangallo");
			inicializarDistrict("Los Morochucos", "Cangallo");
			inicializarDistrict("Llucca", "Cangallo");
			inicializarDistrict("María Parado de Bellido", "Cangallo");
			inicializarDistrict("Sancos", "Cangallo");
			inicializarDistrict("San Pedro de Larcay", "Cangallo");
			inicializarDistrict("Totos", "Cangallo");

			// Inserción de datos para Districts de Huamanga (Ayacucho)
			inicializarDistrict("Ayacucho", "Huamanga");
			inicializarDistrict("Chiara", "Huamanga");
			inicializarDistrict("Carmen Alto", "Huamanga");
			inicializarDistrict("Jesús Nazareno", "Huamanga");
			inicializarDistrict("San Juan Bautista", "Huamanga");
			inicializarDistrict("San Pedro", "Huamanga");
			inicializarDistrict("Sapallanga", "Huamanga");
			inicializarDistrict("Socos", "Huamanga");

			// Inserción de datos para Districts de Huanca Sancos (Ayacucho)
			inicializarDistrict("Huanca Sancos", "Huanca Sancos");
			inicializarDistrict("San Pedro de Huacarpana", "Huanca Sancos");
			inicializarDistrict("San Juan Bautista de Huancapi", "Huanca Sancos");
			inicializarDistrict("San Miguel", "Huanca Sancos");
			inicializarDistrict("San Sebastián", "Huanca Sancos");
			inicializarDistrict("Santa Rosa", "Huanca Sancos");

			// Inserción de datos para Districts de Huanta (Ayacucho)
			inicializarDistrict("Huanta", "Huanta");
			inicializarDistrict("Ayahuanco", "Huanta");
			inicializarDistrict("Huancapi", "Huanta");
			inicializarDistrict("Llochegua", "Huanta");
			inicializarDistrict("Rondos", "Huanta");
			inicializarDistrict("San Antonio de Cachi", "Huanta");
			inicializarDistrict("San Miguel", "Huanta");

			// Inserción de datos para Districts de La Mar (Ayacucho)
			inicializarDistrict("San Miguel", "La Mar");
			inicializarDistrict("Santa Rosa", "La Mar");
			inicializarDistrict("Tambo", "La Mar");
			inicializarDistrict("Viquia", "La Mar");
			inicializarDistrict("Zapata", "La Mar");

			// Inserción de datos para Districts de Lucanas (Ayacucho)
			inicializarDistrict("Lucanas", "Lucanas");
			inicializarDistrict("Acarí", "Lucanas");
			inicializarDistrict("Ate", "Lucanas");
			inicializarDistrict("Chaviña", "Lucanas");
			inicializarDistrict("Laramate", "Lucanas");
			inicializarDistrict("Ocaña", "Lucanas");
			inicializarDistrict("San Cristóbal", "Lucanas");
			inicializarDistrict("San Juan de Lucanas", "Lucanas");
			inicializarDistrict("San Pedro", "Lucanas");

			// Inserción de datos para Districts de Parinacochas (Ayacucho)
			inicializarDistrict("Parinacochas", "Parinacochas");
			inicializarDistrict("Cora Cora", "Parinacochas");
			inicializarDistrict("Oropesa", "Parinacochas");
			inicializarDistrict("San José de Ushua", "Parinacochas");
			inicializarDistrict("San Luis", "Parinacochas");

			// Inserción de datos para Districts de Páucar del Sara Sara (Ayacucho)
			inicializarDistrict("Páucar del Sara Sara", "Páucar del Sara Sara");
			inicializarDistrict("Chilcas", "Páucar del Sara Sara");
			inicializarDistrict("Huacara", "Páucar del Sara Sara");
			inicializarDistrict("Mollepampa", "Páucar del Sara Sara");
			inicializarDistrict("Santiago de Paucaray", "Páucar del Sara Sara");
			inicializarDistrict("Sara Sara", "Páucar del Sara Sara");

			// Inserción de datos para Districts de Sucre (Ayacucho)
			inicializarDistrict("Sucre", "Sucre");
			inicializarDistrict("Azángaro", "Sucre");
			inicializarDistrict("Cuyo", "Sucre");
			inicializarDistrict("Huancarama", "Sucre");
			inicializarDistrict("San Juan de Churín", "Sucre");
			inicializarDistrict("Sancos", "Sucre");

			// Inserción de datos para Districts de Víctor Fajardo (Ayacucho)
			inicializarDistrict("Víctor Fajardo", "Víctor Fajardo");
			inicializarDistrict("Carmen Alto", "Víctor Fajardo");
			inicializarDistrict("Cangallo", "Víctor Fajardo");
			inicializarDistrict("San Francisco de Asís", "Víctor Fajardo");
			inicializarDistrict("San Juan de Lurigancho", "Víctor Fajardo");

			// Inserción de datos para Districts de Vilcas Huamán (Ayacucho)
			inicializarDistrict("Vilcas Huamán", "Vilcas Huamán");
			inicializarDistrict("Tambo", "Vilcas Huamán");
			inicializarDistrict("San Juan de Lucanas", "Vilcas Huamán");
			inicializarDistrict("San Miguel", "Vilcas Huamán");
			inicializarDistrict("Santa Rosa", "Vilcas Huamán");

			// Inserción de datos para Districts de Chincha (Ica)
			inicializarDistrict("Chincha Alta", "Chincha");
			inicializarDistrict("Chincha Baja", "Chincha");
			inicializarDistrict("El Carmen", "Chincha");
			inicializarDistrict("Grocio Prado", "Chincha");
			inicializarDistrict("Pueblo Nuevo", "Chincha");
			inicializarDistrict("San Juan Bautista", "Chincha");
			inicializarDistrict("San Pedro de Huacarpana", "Chincha");

			// Inserción de datos para Districts de Ica (Ica)
			inicializarDistrict("Ica", "Ica");
			inicializarDistrict("La Tinguiña", "Ica");
			inicializarDistrict("Los Aquijes", "Ica");
			inicializarDistrict("Ocucaje", "Ica");
			inicializarDistrict("Pachacutec", "Ica");
			inicializarDistrict("Parcona", "Ica");
			inicializarDistrict("Salas Guadalupe", "Ica");
			inicializarDistrict("San José de los Molinos", "Ica");
			inicializarDistrict("San Juan Bautista", "Ica");
			inicializarDistrict("Santiago", "Ica");

			// Inserción de datos para Districts de Nasca (Ica)
			inicializarDistrict("Nasca", "Nasca");
			inicializarDistrict("Changuillo", "Nasca");
			inicializarDistrict("El Ingenio", "Nasca");
			inicializarDistrict("Marcona", "Nasca");
			inicializarDistrict("Vista Alegre", "Nasca");

			// Inserción de datos para Districts de Palpa (Ica)
			inicializarDistrict("Palpa", "Palpa");
			inicializarDistrict("Nasca", "Palpa");
			inicializarDistrict("Santa Ana", "Palpa");
			inicializarDistrict("Santa Elena", "Palpa");
			inicializarDistrict("Tibillo", "Palpa");

			// Inserción de datos para Districts de Pisco (Ica)
			inicializarDistrict("Pisco", "Pisco");
			inicializarDistrict("Huancano", "Pisco");
			inicializarDistrict("Humay", "Pisco");
			inicializarDistrict("La Unión", "Pisco");
			inicializarDistrict("Ocucaje", "Pisco");
			inicializarDistrict("San Andrés", "Pisco");
			inicializarDistrict("San Clemente", "Pisco");
			inicializarDistrict("San Luis", "Pisco");
			inicializarDistrict("Túpac Amaru Inca", "Pisco");

			// Inserción de datos para Districts de Chiclayo (Chiclayo)
			inicializarDistrict("Chiclayo", "Chiclayo");
			inicializarDistrict("Chongoyape", "Chiclayo");
			inicializarDistrict("Eten", "Chiclayo");
			inicializarDistrict("Eten Pueblo", "Chiclayo");
			inicializarDistrict("La Victoria", "Chiclayo");
			inicializarDistrict("José Leonardo Ortiz", "Chiclayo");
			inicializarDistrict("Pimentel", "Chiclayo");
			inicializarDistrict("Reque", "Chiclayo");
			inicializarDistrict("Santa Rosa", "Chiclayo");
			inicializarDistrict("Saña", "Chiclayo");

			// Inserción de datos para Districts de Ferreñafe (Chiclayo)
			inicializarDistrict("Ferreñafe", "Ferreñafe");
			inicializarDistrict("Cañaris", "Ferreñafe");
			inicializarDistrict("Incahuasi", "Ferreñafe");
			inicializarDistrict("Jallanca", "Ferreñafe");
			inicializarDistrict("Manuel Antonio Mesones Muro", "Ferreñafe");
			inicializarDistrict("Pítipo", "Ferreñafe");
			inicializarDistrict("Pueblo Nuevo", "Ferreñafe");

			// Inserción de datos para Districts de Lambayeque (Chiclayo)
			inicializarDistrict("Lambayeque", "Lambayeque");
			inicializarDistrict("Chiclayo", "Lambayeque");
			inicializarDistrict("José Leonardo Ortiz", "Lambayeque");
			inicializarDistrict("Lambayeque", "Lambayeque");
			inicializarDistrict("Pítipo", "Lambayeque");
			inicializarDistrict("Pueblo Nuevo", "Lambayeque");
			inicializarDistrict("Reque", "Lambayeque");
			inicializarDistrict("Saña", "Lambayeque");
			inicializarDistrict("Santa Rosa", "Lambayeque");

			// Inserción de datos para Districts de Acomayo (Cuzco)
			inicializarDistrict("Acomayo", "Acomayo");
			inicializarDistrict("Acos", "Acomayo");
			inicializarDistrict("Acomayo", "Acomayo");
			inicializarDistrict("Capaya", "Acomayo");
			inicializarDistrict("Huarocondo", "Acomayo");
			inicializarDistrict("Oropesa", "Acomayo");
			inicializarDistrict("Rondocan", "Acomayo");

			// Inserción de datos para Districts de Anta (Cuzco)
			inicializarDistrict("Anta", "Anta");
			inicializarDistrict("Cachimayo", "Anta");
			inicializarDistrict("Chinchaypucyo", "Anta");
			inicializarDistrict("Huaripampa", "Anta");
			inicializarDistrict("Layo", "Anta");
			inicializarDistrict("Mollepata", "Anta");
			inicializarDistrict("Pachacutec", "Anta");
			inicializarDistrict("Pillcopata", "Anta");
			inicializarDistrict("Puente", "Anta");
			inicializarDistrict("Rondocan", "Anta");
			inicializarDistrict("Sicuani", "Anta");
			inicializarDistrict("Sutro", "Anta");

			// Inserción de datos para Districts de Calca (Cuzco)
			inicializarDistrict("Calca", "Calca");
			inicializarDistrict("Coya", "Calca");
			inicializarDistrict("Lamay", "Calca");
			inicializarDistrict("Machu Picchu", "Calca");
			inicializarDistrict("Ollantaytambo", "Calca");
			inicializarDistrict("Pisaq", "Calca");
			inicializarDistrict("San Salvador", "Calca");
			inicializarDistrict("San Sebastián", "Calca");

			// Inserción de datos para Districts de Canas (Cuzco)
			inicializarDistrict("Canas", "Canas");
			inicializarDistrict("Checca", "Canas");
			inicializarDistrict("Kunturkanki", "Canas");
			inicializarDistrict("Langui", "Canas");
			inicializarDistrict("Layo", "Canas");
			inicializarDistrict("Pampamarca", "Canas");
			inicializarDistrict("Tucy", "Canas");

			// Inserción de datos para Districts de Canchis (Cuzco)
			inicializarDistrict("Canchis", "Canchis");
			inicializarDistrict("Checacupe", "Canchis");
			inicializarDistrict("Combapata", "Canchis");
			inicializarDistrict("Marangani", "Canchis");
			inicializarDistrict("Pitumarca", "Canchis");
			inicializarDistrict("San Pablo", "Canchis");
			inicializarDistrict("San Pedro", "Canchis");

			// Inserción de datos para Districts de Chumbivilcas (Cuzco)
			inicializarDistrict("Chumbivilcas", "Chumbivilcas");
			inicializarDistrict("Ccarhuayo", "Chumbivilcas");
			inicializarDistrict("Colquemarca", "Chumbivilcas");
			inicializarDistrict("Livitaca", "Chumbivilcas");
			inicializarDistrict("Santo Tomás", "Chumbivilcas");
			inicializarDistrict("Sicuani", "Chumbivilcas");

			// Inserción de datos para Districts de Cusco (Cuzco)
			inicializarDistrict("Cusco", "Cusco");
			inicializarDistrict("Alto San Pedro", "Cusco");
			inicializarDistrict("Centro Histórico", "Cusco");
			inicializarDistrict("Carmen Alto", "Cusco");
			inicializarDistrict("Cayma", "Cusco");
			inicializarDistrict("Ccoripata", "Cusco");
			inicializarDistrict("San Sebastián", "Cusco");
			inicializarDistrict("San Jerónimo", "Cusco");
			inicializarDistrict("San Blas", "Cusco");

			// Inserción de datos para Districts de Espinar (Cuzco)
			inicializarDistrict("Espinar", "Espinar");
			inicializarDistrict("Ccochaccasa", "Espinar");
			inicializarDistrict("Colquemarca", "Espinar");
			inicializarDistrict("Pallpata", "Espinar");
			inicializarDistrict("Sicuani", "Espinar");
			inicializarDistrict("Yauri", "Espinar");

			// Inserción de datos para Districts de La Convención (Cuzco)
			inicializarDistrict("La Convención", "La Convención");
			inicializarDistrict("Echarate", "La Convención");
			inicializarDistrict("Kimbiri", "La Convención");
			inicializarDistrict("Maranura", "La Convención");
			inicializarDistrict("Quillabamba", "La Convención");
			inicializarDistrict("Santa Ana", "La Convención");

			// Inserción de datos para Districts de Paruro (Cuzco)
			inicializarDistrict("Paruro", "Paruro");
			inicializarDistrict("Ccollpani", "Paruro");
			inicializarDistrict("Huanoquite", "Paruro");
			inicializarDistrict("Paccaritambo", "Paruro");
			inicializarDistrict("Pacchanta", "Paruro");
			inicializarDistrict("Yanaoca", "Paruro");

			// Inserción de datos para Districts de Paucartambo (Cuzco)
			inicializarDistrict("Paucartambo", "Paucartambo");
			inicializarDistrict("Caicay", "Paucartambo");
			inicializarDistrict("Colquepata", "Paucartambo");
			inicializarDistrict("Huinchiri", "Paucartambo");
			inicializarDistrict("Kosñipata", "Paucartambo");
			inicializarDistrict("Paucartambo", "Paucartambo");

			// Inserción de datos para Districts de Quispicanchi (Cuzco)
			inicializarDistrict("Urcos", "Quispicanchi");
			inicializarDistrict("Andahuaylillas", "Quispicanchi");
			inicializarDistrict("Oropesa", "Quispicanchi");
			inicializarDistrict("San Jerónimo", "Quispicanchi");
			inicializarDistrict("San Juan de Quihuar", "Quispicanchi");
			inicializarDistrict("San Pablo", "Quispicanchi");
			inicializarDistrict("San Pedro", "Quispicanchi");

			// Inserción de datos para Districts de Urubamba (Cuzco)
			inicializarDistrict("Urubamba", "Urubamba");
			inicializarDistrict("Calca", "Urubamba");
			inicializarDistrict("Ollantaytambo", "Urubamba");
			inicializarDistrict("Yucay", "Urubamba");

			// Inserción de datos para Districts de Cajabamba (Cajamarca)
			inicializarDistrict("Cajabamba", "Cajabamba");
			inicializarDistrict("Condebamba", "Cajabamba");
			inicializarDistrict("Jacas Grande", "Cajabamba");
			inicializarDistrict("Judas", "Cajabamba");
			inicializarDistrict("La Libertad de Pallán", "Cajabamba");
			inicializarDistrict("San Miguel", "Cajabamba");

			// Inserción de datos para Districts de Cajamarca (Cajamarca)
			inicializarDistrict("Cajamarca", "Cajamarca");
			inicializarDistrict("Asunción", "Cajamarca");
			inicializarDistrict("Baños del Inca", "Cajamarca");
			inicializarDistrict("Cajabamba", "Cajamarca");
			inicializarDistrict("Chancay", "Cajamarca");
			inicializarDistrict("Chetilla", "Cajamarca");
			inicializarDistrict("Cujaca", "Cajamarca");
			inicializarDistrict("La Encañada", "Cajamarca");
			inicializarDistrict("Los Baños del Inca", "Cajamarca");
			inicializarDistrict("Namora", "Cajamarca");
			inicializarDistrict("San Juan", "Cajamarca");
			inicializarDistrict("San Pablo", "Cajamarca");
			inicializarDistrict("San Miguel", "Cajamarca");
			inicializarDistrict("San Silvestre", "Cajamarca");

			// Inserción de datos para Districts de Celendín (Cajamarca)
			inicializarDistrict("Celendín", "Celendín");
			inicializarDistrict("Chumuch", "Celendín");
			inicializarDistrict("Huasmin", "Celendín");
			inicializarDistrict("Jaque", "Celendín");
			inicializarDistrict("Llamac", "Celendín");
			inicializarDistrict("San Juan de Celendín", "Celendín");
			inicializarDistrict("Santos", "Celendín");

			// Inserción de datos para Districts de Chota (Cajamarca)
			inicializarDistrict("Chota", "Chota");
			inicializarDistrict("Chalamarca", "Chota");
			inicializarDistrict("Chiguirip", "Chota");
			inicializarDistrict("Llamabamba", "Chota");
			inicializarDistrict("Llama", "Chota");
			inicializarDistrict("Marta", "Chota");
			inicializarDistrict("San Juan de Chota", "Chota");

			// Inserción de datos para Districts de Contumazá (Cajamarca)
			inicializarDistrict("Contumazá", "Contumazá");
			inicializarDistrict("Chancaybaños", "Contumazá");
			inicializarDistrict("Curgos", "Contumazá");
			inicializarDistrict("San Juan", "Contumazá");
			inicializarDistrict("San Martín", "Contumazá");

			// Inserción de datos para Districts de Cutervo (Cajamarca)
			inicializarDistrict("Cutervo", "Cutervo");
			inicializarDistrict("Choropampa", "Cutervo");
			inicializarDistrict("La Ramada", "Cutervo");
			inicializarDistrict("San Andrés", "Cutervo");
			inicializarDistrict("San Juan de Cutervo", "Cutervo");
			inicializarDistrict("Santo Tomás", "Cutervo");

			// Inserción de datos para Districts de Hualgayoc (Cajamarca)
			inicializarDistrict("Hualgayoc", "Hualgayoc");
			inicializarDistrict("Cascas", "Hualgayoc");
			inicializarDistrict("Chancay", "Hualgayoc");
			inicializarDistrict("La Ramada", "Hualgayoc");
			inicializarDistrict("San Juan de Hualgayoc", "Hualgayoc");

			// Inserción de datos para Districts de Jaén (Cajamarca)
			inicializarDistrict("Jaén", "Jaén");
			inicializarDistrict("Bagua Grande", "Jaén");
			inicializarDistrict("Jumbilla", "Jaén");
			inicializarDistrict("La Peñita", "Jaén");
			inicializarDistrict("San Ignacio", "Jaén");

			// Inserción de datos para Districts de San Ignacio (Cajamarca)
			inicializarDistrict("San Ignacio", "San Ignacio");
			inicializarDistrict("Chirinos", "San Ignacio");
			inicializarDistrict("Huarango", "San Ignacio");
			inicializarDistrict("San José", "San Ignacio");
			inicializarDistrict("San Pablo", "San Ignacio");

			// Inserción de datos para Districts de San Marcos (Cajamarca)
			inicializarDistrict("San Marcos", "San Marcos");
			inicializarDistrict("San Miguel", "San Marcos");
			inicializarDistrict("San Pedro", "San Marcos");
			inicializarDistrict("San Rafael", "San Marcos");
			inicializarDistrict("Santa Rosa", "San Marcos");

			// Inserción de datos para Districts de San Miguel (Cajamarca)
			inicializarDistrict("San Miguel", "San Miguel");
			inicializarDistrict("Asunción", "San Miguel");
			inicializarDistrict("Chalamarca", "San Miguel");
			inicializarDistrict("Chota", "San Miguel");
			inicializarDistrict("La Ramada", "San Miguel");

			// Inserción de datos para Districts de San Pablo (Cajamarca)
			inicializarDistrict("San Pablo", "San Pablo");
			inicializarDistrict("Cascas", "San Pablo");
			inicializarDistrict("Chancay", "San Pablo");
			inicializarDistrict("Llama", "San Pablo");
			inicializarDistrict("San Juan de La Unión", "San Pablo");

			// Inserción de datos para Districts de Santa Cruz (Cajamarca)
			inicializarDistrict("Santa Cruz", "Santa Cruz");
			inicializarDistrict("Andabamba", "Santa Cruz");
			inicializarDistrict("Chancay", "Santa Cruz");
			inicializarDistrict("Hualgayoc", "Santa Cruz");
			inicializarDistrict("La Ramada", "Santa Cruz");

			// Inserción de datos para Districts de Ascope (Trujillo)
			inicializarDistrict("Ascope", "Ascope");
			inicializarDistrict("Calamarca", "Ascope");
			inicializarDistrict("Chicama", "Ascope");
			inicializarDistrict("Coscomba", "Ascope");
			inicializarDistrict("Guadalupe", "Ascope");
			inicializarDistrict("Paiján", "Ascope");
			inicializarDistrict("Rázuri", "Ascope");
			inicializarDistrict("Sinsicap", "Ascope");

			// Inserción de datos para Districts de Bolívar (Trujillo)
			inicializarDistrict("Bolívar", "Bolívar");
			inicializarDistrict("Buldibuyo", "Bolívar");
			inicializarDistrict("Condormarca", "Bolívar");
			inicializarDistrict("Longotea", "Bolívar");
			inicializarDistrict("San Agustín", "Bolívar");
			inicializarDistrict("San Juan", "Bolívar");
			inicializarDistrict("San Miguel", "Bolívar");
			inicializarDistrict("San Vicente", "Bolívar");

			// Inserción de datos para Districts de Chepén (Trujillo)
			inicializarDistrict("Chepén", "Chepén");
			inicializarDistrict("Pacanga", "Chepén");
			inicializarDistrict("Pomahuaca", "Chepén");
			inicializarDistrict("Río Seco", "Chepén");

			// Inserción de datos para Districts de Gran Chimú (Trujillo)
			inicializarDistrict("Gran Chimú", "Gran Chimú");
			inicializarDistrict("Moche", "Gran Chimú");
			inicializarDistrict("Poroto", "Gran Chimú");
			inicializarDistrict("Victor Larco", "Gran Chimú");

			// Inserción de datos para Districts de Julcán (Trujillo)
			inicializarDistrict("Julcán", "Julcán");
			inicializarDistrict("Cascas", "Julcán");
			inicializarDistrict("Huaso", "Julcán");
			inicializarDistrict("Julcán", "Julcán");
			inicializarDistrict("Sinsicap", "Julcán");

			// Inserción de datos para Districts de Otuzco (Trujillo)
			inicializarDistrict("Otuzco", "Otuzco");
			inicializarDistrict("Agallpampa", "Otuzco");
			inicializarDistrict("Carabamba", "Otuzco");
			inicializarDistrict("Casca", "Otuzco");
			inicializarDistrict("Mascar", "Otuzco");
			inicializarDistrict("Salpo", "Otuzco");
			inicializarDistrict("Sinsicap", "Otuzco");

			// Inserción de datos para Districts de Pacasmayo (Trujillo)
			inicializarDistrict("Pacasmayo", "Pacasmayo");
			inicializarDistrict("San Pedro de Lloc", "Pacasmayo");
			inicializarDistrict("Guadalupe", "Pacasmayo");
			inicializarDistrict("Jequetepeque", "Pacasmayo");
			inicializarDistrict("Pataz", "Pacasmayo");

			// Inserción de datos para Districts de Pataz (Trujillo)
			inicializarDistrict("Pataz", "Pataz");
			inicializarDistrict("Buldibuyo", "Pataz");
			inicializarDistrict("Chillia", "Pataz");
			inicializarDistrict("Huaylillas", "Pataz");
			inicializarDistrict("Patáz", "Pataz");

			// Inserción de datos para Districts de Sánchez Carrión (Trujillo)
			inicializarDistrict("Sánchez Carrión", "Sánchez Carrión");
			inicializarDistrict("Julcán", "Sánchez Carrión");
			inicializarDistrict("Sinsicap", "Sánchez Carrión");
			inicializarDistrict("Ticabamba", "Sánchez Carrión");
			inicializarDistrict("Sánchez Carrión", "Sánchez Carrión");

			// Inserción de datos para Districts de Santiago de Chuco (Trujillo)
			inicializarDistrict("Santiago de Chuco", "Santiago de Chuco");
			inicializarDistrict("Angasmarca", "Santiago de Chuco");
			inicializarDistrict("Chugay", "Santiago de Chuco");
			inicializarDistrict("Mollepata", "Santiago de Chuco");
			inicializarDistrict("Santiago de Chuco", "Santiago de Chuco");

			// Inserción de datos para Districts de Trujillo (Trujillo)
			inicializarDistrict("Trujillo", "Trujillo");
			inicializarDistrict("El Porvenir", "Trujillo");
			inicializarDistrict("La Esperanza", "Trujillo");
			inicializarDistrict("La Libertad", "Trujillo");
			inicializarDistrict("Salaverry", "Trujillo");
			inicializarDistrict("Víctor Larco", "Trujillo");

			// Inserción de datos para Districts de Virú (Trujillo)
			inicializarDistrict("Virú", "Virú");
			inicializarDistrict("Chao", "Virú");
			inicializarDistrict("Guadalupito", "Virú");
			inicializarDistrict("Paiján", "Virú");
			inicializarDistrict("Santiago de Cao", "Virú");

			// Inserción de datos para Districts de Ayabaca (Piura)
			inicializarDistrict("Ayabaca", "Ayabaca");
			inicializarDistrict("Frías", "Ayabaca");
			inicializarDistrict("Jepelacio", "Ayabaca");
			inicializarDistrict("Lagunas", "Ayabaca");
			inicializarDistrict("Pacaipampa", "Ayabaca");
			inicializarDistrict("Paimas", "Ayabaca");
			inicializarDistrict("Sicchez", "Ayabaca");
			inicializarDistrict("Tambo Grande", "Ayabaca");
			inicializarDistrict("Yamango", "Ayabaca");

			// Inserción de datos para Districts de Huancabamba (Piura)
			inicializarDistrict("Huancabamba", "Huancabamba");
			inicializarDistrict("Canchaque", "Huancabamba");
			inicializarDistrict("El Carmen de la Frontera", "Huancabamba");
			inicializarDistrict("Filo de la Sierra", "Huancabamba");
			inicializarDistrict("San José de Lourdes", "Huancabamba");
			inicializarDistrict("Santo Domingo", "Huancabamba");
			inicializarDistrict("Suyo", "Huancabamba");

			// Inserción de datos para Districts de Morropón (Piura)
			inicializarDistrict("Chulucanas", "Morropón");
			inicializarDistrict("Morropón", "Morropón");
			inicializarDistrict("Salitral", "Morropón");
			inicializarDistrict("San Juan de Bigote", "Morropón");
			inicializarDistrict("San Luis", "Morropón");
			inicializarDistrict("San Pedro del Huancayoc", "Morropón");
			inicializarDistrict("Santo Domingo", "Morropón");
			inicializarDistrict("Sullana", "Morropón");

			// Inserción de datos para Districts de Paita (Piura)
			inicializarDistrict("Paita", "Paita");
			inicializarDistrict("Amotape", "Paita");
			inicializarDistrict("Colán", "Paita");
			inicializarDistrict("El Arenal", "Paita");
			inicializarDistrict("La Huaca", "Paita");
			inicializarDistrict("Máncora", "Paita");
			inicializarDistrict("Vichayal", "Paita");

			// Inserción de datos para Districts de Piura (Piura)
			inicializarDistrict("Piura", "Piura");
			inicializarDistrict("Catacaos", "Piura");
			inicializarDistrict("Cura Mori", "Piura");
			inicializarDistrict("El Tallán", "Piura");
			inicializarDistrict("La Arena", "Piura");
			inicializarDistrict("La Unión", "Piura");
			inicializarDistrict("Las Lomas", "Piura");
			inicializarDistrict("Tambo Grande", "Piura");
			inicializarDistrict("Vichayal", "Piura");

			// Inserción de datos para Districts de Sechura (Piura)
			inicializarDistrict("Sechura", "Sechura");
			inicializarDistrict("Bernal", "Sechura");
			inicializarDistrict("Vice", "Sechura");
			inicializarDistrict("Paita", "Sechura");

			// Inserción de datos para Districts de Sullana (Piura)
			inicializarDistrict("Sullana", "Sullana");
			inicializarDistrict("Bellavista", "Sullana");
			inicializarDistrict("Chulucanas", "Sullana");
			inicializarDistrict("La Arena", "Sullana");
			inicializarDistrict("Luis Felipe de la Fuente", "Sullana");
			inicializarDistrict("Marcavelica", "Sullana");
			inicializarDistrict("Morropón", "Sullana");
			inicializarDistrict("Sullana", "Sullana");

			// Inserción de datos para Districts de Talara (Piura)
			inicializarDistrict("Talara", "Talara");
			inicializarDistrict("El Alto", "Talara");
			inicializarDistrict("La Brea", "Talara");
			inicializarDistrict("Lobitos", "Talara");
			inicializarDistrict("Máncora", "Talara");
			inicializarDistrict("Punta Sal", "Talara"); */

		// Inserción de datos para Role
		initializeRole("ADMIN");
		initializeRole("PERSONAL");
		initializeRole("USUARIO");

        // Inserción de datos para Contact Status
        initializeContactStatus("PENDIENTE");
        initializeContactStatus("REVISADO");
        initializeContactStatus("RESPONDIDO");
        initializeContactStatus("CANCELADO");

        // Inserción de datos para Payment Methods
        initializePaymentMethod("BANK TRANSFER");
        initializePaymentMethod("CASH");
        initializePaymentMethod("CREDIT CARD");
  }
  

    // Country
    private void inicializarCountry(String countryName) {
		Optional<Country> optional = countryRepository.findByName(countryName);
		if (optional.isPresent()) {
			Country country = optional.get();
			country.setName(countryName);
			countryRepository.save(country);
		} else {
			Country country = new Country();
			country.setName(countryName);
			countryRepository.save(country);
		}
	}

    // State
    private void inicializarState(String stateName, String country) {
		Optional<State> optional = stateRepository.findByName(stateName);
		Optional<Country> countryOptional = countryRepository.findByName(country);

		if (optional.isPresent()) {
			State state = optional.get();
			if (countryOptional.isPresent()) {
				state.setCountry(countryOptional.get());
			}
			state.setName(stateName);
			stateRepository.save(state);
		} else {
			State state = new State();
			state.setName(stateName);
			countryOptional.ifPresent(state::setCountry);
			stateRepository.save(state);
		}
	}
    
    // Provinces
    private void inicializarProvince(String provinceName, String stateName) {
		Optional<Province> optional = provinceRepository.findByName(provinceName);
		Optional<State> stateOptional = stateRepository.findByName(stateName);

		if (optional.isPresent()) {
			Province province = optional.get();
			if (stateOptional.isPresent()) {
				province.setState(stateOptional.get());
			}
			province.setName(provinceName);
			provinceRepository.save(province);
		} else {
			Province province = new Province();
			province.setName(provinceName);
			stateOptional.ifPresent(province::setState);
			provinceRepository.save(province);
		}
	}
    // Districts
    private void inicializarDistrict(String districtName, String provinceName) {
		Optional<Districts> optional = districtsRepository.findByName(districtName);
		Optional<Province> provinceOptional = provinceRepository.findByName(provinceName);

		if (optional.isPresent()) {
			Districts district = optional.get();
			if (provinceOptional.isPresent()) {
				district.setProvince(provinceOptional.get());
			}
			district.setName(districtName);
			districtsRepository.save(district);
		} else {
			Districts district = new Districts();
			district.setName(districtName);
			provinceOptional.ifPresent(district::setProvince);
			districtsRepository.save(district);
		}
	}

	// Roles
	private void initializeRole(String roleName) {
		Optional<Roles> existingRole = rolesRepository.findByName(roleName);
		if (existingRole.isEmpty()) {
			Roles role = new Roles();
			role.setName(roleName);
			rolesRepository.save(role);
		}
	}

    // Contact Status
    private void initializeContactStatus(String contactStatus) {
        Optional<StatusContact> existingStatus = statusContactRepository.findByName(contactStatus);
        if (existingStatus.isEmpty()) {
            StatusContact status = new StatusContact();
            status.setName(contactStatus);
            statusContactRepository.save(status);
        }
    }

    // Payment Method
    private void initializePaymentMethod(String paymentMethod) {
        Optional<PaymentMethod> existingPaymentMethod = paymentMethodRepository.findByName(paymentMethod);
        if (existingPaymentMethod.isEmpty()) {
            PaymentMethod payment = new PaymentMethod();
            payment.setName(paymentMethod);
            paymentMethodRepository.save(payment);
        }
    }
}
