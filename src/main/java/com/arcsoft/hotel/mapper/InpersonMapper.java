package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Inperson;
import com.arcsoft.hotel.pojo.InpersonExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface InpersonMapper {
    @SelectProvider(type = InpersonSqlProvider.class, method = "countByExample")
    int countByExample(InpersonExample example);

    @DeleteProvider(type = InpersonSqlProvider.class, method = "deleteByExample")
    int deleteByExample(InpersonExample example);

    @Delete({
            "delete from inperson",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into inperson (checkin_id, name, ",
            "document_type, document_number, ",
            "phone_number, faceurl, ",
            "is_check_out, face)",
            "values (#{checkinId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{documentType,jdbcType=INTEGER}, #{documentNumber,jdbcType=VARCHAR}, ",
            "#{phoneNumber,jdbcType=VARCHAR}, #{faceurl,jdbcType=VARCHAR}, ",
            "#{isCheckOut,jdbcType=TINYINT}, #{face,jdbcType=VARBINARY})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(Inperson record);

    @InsertProvider(type = InpersonSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(Inperson record);

    @SelectProvider(type = InpersonSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "faceurl", property = "faceurl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_check_out", property = "isCheckOut", jdbcType = JdbcType.TINYINT),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<Inperson> selectByExampleWithBLOBs(InpersonExample example);

    @SelectProvider(type = InpersonSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "faceurl", property = "faceurl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_check_out", property = "isCheckOut", jdbcType = JdbcType.TINYINT)
    })
    List<Inperson> selectByExample(InpersonExample example);

    @Select({
            "select",
            "id, checkin_id, name, document_type, document_number, phone_number, faceurl, ",
            "is_check_out, face",
            "from inperson",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "faceurl", property = "faceurl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_check_out", property = "isCheckOut", jdbcType = JdbcType.TINYINT),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    Inperson selectByPrimaryKey(Integer id);

    @UpdateProvider(type = InpersonSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Inperson record, @Param("example") InpersonExample example);

    @UpdateProvider(type = InpersonSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Inperson record, @Param("example") InpersonExample example);

    @UpdateProvider(type = InpersonSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Inperson record, @Param("example") InpersonExample example);

    @UpdateProvider(type = InpersonSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Inperson record);

    @Update({
            "update inperson",
            "set checkin_id = #{checkinId,jdbcType=INTEGER},",
            "name = #{name,jdbcType=VARCHAR},",
            "document_type = #{documentType,jdbcType=INTEGER},",
            "document_number = #{documentNumber,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "faceurl = #{faceurl,jdbcType=VARCHAR},",
            "is_check_out = #{isCheckOut,jdbcType=TINYINT},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Inperson record);

    @Update({
            "update inperson",
            "set checkin_id = #{checkinId,jdbcType=INTEGER},",
            "name = #{name,jdbcType=VARCHAR},",
            "document_type = #{documentType,jdbcType=INTEGER},",
            "document_number = #{documentNumber,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "faceurl = #{faceurl,jdbcType=VARCHAR},",
            "is_check_out = #{isCheckOut,jdbcType=TINYINT}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Inperson record);
}