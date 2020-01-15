package it.pcan.test.feign.client;

import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 *
 * @author Pierantonio Cangianiello
 */
public interface FeignClientNode {


//    @RequestLine("GET /batch/node/list/{id}/{name}")
//    public Map file(@Param("id") String id,@Param("name")String name);
//    @RequestLine("GET /batch/node/list/{type}/{name}/{version}")
//    public Map finddataflow(@Param("type") String type,@Param("name") String name,@Param("version") String version);
    @RequestLine("GET /batch/node/list/{type}/{name}/{version}")
    public JsonResult finResult(@Param("type") String type,@Param("name") String name,@Param("version") String version);
}
