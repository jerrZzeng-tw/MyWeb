package tw.gov.idb.base.dao;

import org.apache.ibatis.annotations.Mapper;
import tw.gov.idb.base.domain.Cityarea;

import java.util.List;

@Mapper
public interface CityareaDao {

    List<Cityarea> selectCityArea();
}