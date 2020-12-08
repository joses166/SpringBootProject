package br.com.erudio.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.erudio.config.FileStorageConfig;
import br.com.erudio.exception.FileStorageException;
import br.com.erudio.exception.MyFileNotFoundException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		// Pega o diretório configurado
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		// Cria o diretório para salvar os arquivos ou manda exceção
		try {
			if (!Files.exists(this.fileStorageLocation))
				Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException
			("Could not create the directory where the uploaded files will be stored.", e);
		}
	}
	
	/**
	 * Metodo para armazenar o arquivo
	 * @param file
	 * @return
	 */
	public String storeFile(MultipartFile file) {
		// Pega o diretório e limpa as informações de espaços ou caracteres que podem dar problema
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			
			// Verifica se existem dois pontos entre o nome e a extensão info..txt, caso tenha retorna exceção
			if (fileName.contains("..")) 
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			// Nessa parte o sistema irá pegar os bits do arquivo e irá mover para o diretório informado 
			// com o nome do arquivo e irá substituir, caso exista algum arquivo no diretório com o mesmo nome
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
		} catch (Exception e) {
			// Retorna uma exceção caso ocorra algum problema no processo de salvamento do arquivo
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
	}
	
	/**
	 * Metodo para download de arquivos
	 * @param fileName
	 * @return
	 */
	public Resource loadFileAsResource(String fileName) {
		try {
			// Puxa a localização do arquivo
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			// Retorna o arquivo caso exista
			Resource resource = new UrlResource(filePath.toUri());
			// Valida se existe algo
			if (resource.exists()) return resource;
			else throw new MyFileNotFoundException("File not found " + fileName);
		} catch (Exception e) {
			throw new MyFileNotFoundException("File not found " + fileName, e);
		}
	}

}
