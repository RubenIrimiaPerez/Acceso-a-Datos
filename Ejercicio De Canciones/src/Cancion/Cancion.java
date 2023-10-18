package Cancion;



public class Cancion {
	private int Id;
	private String Titulo;
	private String Artista;
	private String Minutos;
	private String Album;
	private String Letra;
	
	
	public Cancion(int Id, String titulo, String artista, String minutos, String album, String letra) {
		this.Id = Id;
		this.Titulo = titulo;
		this.Artista = artista;
		this.Minutos = minutos;
		this.Album = album;
		this.Letra = letra;
	}


	public Cancion() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return Id;
	}


	public void setId(int Id) {
		this.Id = Id;
	}


	public String getTitulo() {
		return Titulo;
	}


	public void setTitulo(String titulo) {
		Titulo = titulo;
	}


	public String getArtista() {
		return Artista;
	}


	public void setArtista(String artista) {
		Artista = artista;
	}


	public String getMinutos() {
		return Minutos;
	}


	public void setMinutos(String Minutos) {
		this.Minutos = Minutos;
	}


	public String getAlbum() {
		return Album;
	}


	public void setAlbum(String album) {
		Album = album;
	}


	public String getLetra() {
		return Letra;
	}


	public void setLetra(String letra) {
		Letra = letra;
	}
	


}
