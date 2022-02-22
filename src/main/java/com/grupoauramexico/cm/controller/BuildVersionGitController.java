/**
 * 
 */
package com.grupoauramexico.cm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grupoauramexico.cm.dto.BuildVersionInfoDTO;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;

/**
 * @author vaguire
 *
 */
@Controller("/version/")
public class BuildVersionGitController {
	private static final Logger LOG = LoggerFactory.getLogger(BuildVersionGitController.class);
	private static BuildVersionInfoDTO dto = null;
	private static String json = null;

	private final static String GIT_PROPERTIES_FILE = "git.properties";
	
	/**
	 * 
	 * @return La version de la aplicación con base a la meta-info de git disponible
	 *         al momento de empaquetar el componente
	 */
	@Operation(summary = "Muestra la version del componente", description = "Muestra la version del componente")
	@Get(uri = "/build")
	public BuildVersionInfoDTO buildVersion() {
		if (dto == null) {
			dto = new BuildVersionInfoDTO();
			json = readGitProperties(GIT_PROPERTIES_FILE);
			try {
				final ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
				dto.setBranch(node.get("git.branch").textValue());
				dto.setBuildTime(node.get("git.build.time").textValue());
				dto.setBuildHost(node.get("git.build.host").textValue());
				dto.setCommitCount(node.get("git.total.commit.count").textValue());
				dto.setCommitEmail(node.get("git.commit.user.email").textValue());
				dto.setCommitId(node.get("git.commit.id").textValue());
				dto.setCommitMsg(node.get("git.commit.message.short").textValue());
				dto.setCommitTime(node.get("git.commit.time").textValue());
				dto.setUrlRepo(node.get("git.remote.origin.url").textValue());
				dto.setVersion(node.get("git.build.version").textValue());
				LOG.info("git.total.commit.count :: " + dto.getCommitCount());

			} catch (JsonMappingException e) {
				LOG.error(e.getMessage());
			} catch (JsonProcessingException e) {
				LOG.error(e.getMessage());
			}
		}
		return dto;
	}
	protected String readGitProperties(String file) {
		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream inputStream = classLoader.getResourceAsStream(file);
		try {
			return readFromInputStream(inputStream);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return "Version information could not be retrieved";
		}
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

}
