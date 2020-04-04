package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.UserReserve;
import com.arcsoft.hotel.pojo.UserReserveExample;
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

public interface UserReserveMapper {
    @SelectProvider(type = UserReserveSqlProvider.class, method = "countByExample")
    int countByExample(UserReserveExample example);

    @DeleteProvider(type = UserReserveSqlProvider.class, method = "deleteByExample")
    int deleteByExample(UserReserveExample example);

    @Delete({
            "delete from user_reserve",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into user_reserve (name, checkin_date, ",
            "checkout_date, room_type, ",
            "time, phone_number, ",
            "num, user_id, status, ",
            "checkin_id, face)",
            "values (#{name,jdbcType=VARCHAR}, #{checkinDate,jdbcType=DATE}, ",
            "#{checkoutDate,jdbcType=DATE}, #{roomType,jdbcType=VARCHAR}, ",
            "#{time,jdbcType=TIMESTAMP}, #{phoneNumber,jdbcType=VARCHAR}, ",
            "#{num,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
            "#{checkinId,jdbcType=INTEGER}, #{face,jdbcType=VARBINARY})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(UserReserve record);

    @InsertProvider(type = UserReserveSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(UserReserve record);

    @SelectProvider(type = UserReserveSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "room_type", property = "roomType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "num", property = "num", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<UserReserve> selectByExampleWithBLOBs(UserReserveExample example);

    @SelectProvider(type = UserReserveSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "room_type", property = "roomType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "num", property = "num", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER)
    })
    List<UserReserve> selectByExample(UserReserveExample example);

    @Select({
            "select",
            "id, name, checkin_date, checkout_date, room_type, time, phone_number, num, user_id, ",
            "status, checkin_id, face",
            "from user_reserve",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "room_type", property = "roomType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "num", property = "num", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    UserReserve selectByPrimaryKey(Integer id);

    @UpdateProvider(type = UserReserveSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserReserve record, @Param("example") UserReserveExample example);

    @UpdateProvider(type = UserReserveSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") UserReserve record, @Param("example") UserReserveExample example);

    @UpdateProvider(type = UserReserveSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") UserReserve record, @Param("example") UserReserveExample example);

    @UpdateProvider(type = UserReserveSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserReserve record);

    @Update({
            "update user_reserve",
            "set name = #{name,jdbcType=VARCHAR},",
            "checkin_date = #{checkinDate,jdbcType=DATE},",
            "checkout_date = #{checkoutDate,jdbcType=DATE},",
            "room_type = #{roomType,jdbcType=VARCHAR},",
            "time = #{time,jdbcType=TIMESTAMP},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "num = #{num,jdbcType=INTEGER},",
            "user_id = #{userId,jdbcType=INTEGER},",
            "status = #{status,jdbcType=INTEGER},",
            "checkin_id = #{checkinId,jdbcType=INTEGER},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(UserReserve record);

    @Update({
            "update user_reserve",
            "set name = #{name,jdbcType=VARCHAR},",
            "checkin_date = #{checkinDate,jdbcType=DATE},",
            "checkout_date = #{checkoutDate,jdbcType=DATE},",
            "room_type = #{roomType,jdbcType=VARCHAR},",
            "time = #{time,jdbcType=TIMESTAMP},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "num = #{num,jdbcType=INTEGER},",
            "user_id = #{userId,jdbcType=INTEGER},",
            "status = #{status,jdbcType=INTEGER},",
            "checkin_id = #{checkinId,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserReserve record);
}