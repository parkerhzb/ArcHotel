package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Visitor;
import com.arcsoft.hotel.pojo.VisitorExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface VisitorMapper {
    @SelectProvider(type = VisitorSqlProvider.class, method = "countByExample")
    int countByExample(VisitorExample example);

    @DeleteProvider(type = VisitorSqlProvider.class, method = "deleteByExample")
    int deleteByExample(VisitorExample example);

    @Delete({
            "delete from visitor",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into visitor (id, name, ",
            "phone_number, room_id, ",
            "power, document_type, ",
            "document_number, face)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{phoneNumber,jdbcType=VARCHAR}, #{roomId,jdbcType=INTEGER}, ",
            "#{power,jdbcType=VARCHAR}, #{documentType,jdbcType=INTEGER}, ",
            "#{documentNumber,jdbcType=VARCHAR}, #{face,jdbcType=VARBINARY})"
    })
    int insert(Visitor record);

    @InsertProvider(type = VisitorSqlProvider.class, method = "insertSelective")
    int insertSelective(Visitor record);

    @SelectProvider(type = VisitorSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "power", property = "power", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<Visitor> selectByExampleWithBLOBs(VisitorExample example);

    @SelectProvider(type = VisitorSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "power", property = "power", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR)
    })
    List<Visitor> selectByExample(VisitorExample example);

    @Select({
            "select",
            "id, name, phone_number, room_id, power, document_type, document_number, face",
            "from visitor",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "power", property = "power", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    Visitor selectByPrimaryKey(Integer id);

    @UpdateProvider(type = VisitorSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Visitor record, @Param("example") VisitorExample example);

    @UpdateProvider(type = VisitorSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Visitor record, @Param("example") VisitorExample example);

    @UpdateProvider(type = VisitorSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Visitor record, @Param("example") VisitorExample example);

    @UpdateProvider(type = VisitorSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Visitor record);

    @Update({
            "update visitor",
            "set name = #{name,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "room_id = #{roomId,jdbcType=INTEGER},",
            "power = #{power,jdbcType=VARCHAR},",
            "document_type = #{documentType,jdbcType=INTEGER},",
            "document_number = #{documentNumber,jdbcType=VARCHAR},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Visitor record);

    @Update({
            "update visitor",
            "set name = #{name,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "room_id = #{roomId,jdbcType=INTEGER},",
            "power = #{power,jdbcType=VARCHAR},",
            "document_type = #{documentType,jdbcType=INTEGER},",
            "document_number = #{documentNumber,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Visitor record);
}