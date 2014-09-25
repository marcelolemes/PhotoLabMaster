package br.com.login.bean.users.admin.br.com.login.bean.users.admin.cadastro;

import br.com.login.Dao.AlbumDao;
import br.com.login.Dao.ContratoDao;
import br.com.login.model.Album;
import br.com.login.model.Contrato;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class CadastrarAlbum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6549414550211411055L;
	ContratoDao contDao = new ContratoDao();
	FilenameFilter pastaFilter = new FilenameFilter() {

		@Override
		public boolean accept(File dir, String name) {
			String lowercaseName = name.toLowerCase();
			if (lowercaseName.startsWith("20")) {
				return true;
			} else {
				return false;
			}

		}
	};
    /**
     *
     */

    private Album album = new Album();
    private AlbumDao albumDao = new AlbumDao();
    private String contratoPesquisarNumero;

	public void gerarAlbunsCurso(Contrato contrato) throws Exception {
		File[] pastas;

		File diretorio = new File(contrato.getCaminho());
		pastas = diretorio.listFiles(pastaFilter);
		System.out.println("Albuns Gerados");
		persistirListaAlbum(pastas, contrato);

	}

	public void gravarAlbum() throws Exception {
		album.setContrato(contDao.pesquisarContrato(contratoPesquisarNumero));
		albumDao.Gravar(album);
	}

	public void persistirListaAlbum(File[] albuns, Contrato contrato)
			throws Exception {
		List<Album> listaAlbum = new ArrayList<Album>();

		for (File file : albuns) {
			Album album = new Album();
			album.setNumero(file.getName());
			album.setContrato(contrato);
			album.setStatus(contrato.getStatus());
			listaAlbum.add(album);
		}

		System.out.println("Lista Pronta para persistencia "+listaAlbum.size());

		albumDao.GravarLista(listaAlbum);

	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getContratoPesquisarNumero() {
		return contratoPesquisarNumero;
	}

	public void setContratoPesquisarNumero(String contratoPesquisarNumero) {
		this.contratoPesquisarNumero = contratoPesquisarNumero;
	}

}
