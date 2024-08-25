package com.example.demo.repository.implementation;

import com.example.demo.domain.exception.ResourceMappingException;
import com.example.demo.domain.task.Task;
import com.example.demo.repository.DataSourceConfig;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.mappers.TaskRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImp implements TaskRepository {

    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
            SELECT  t.id as task.id,
                    t.tile as task.title,
                    t.description as task.description,
                    t.expiration_date as task.expiration_date,
                    t.status as task.status
            FROM tasks t
            WHERE id = ?""";

    private final String FIND_ALL_BY_ID = """
            SELECT  t.id as task.id,
                    t.tile as task.title,
                    t.description as task.description,
                    t.expiration_date as task.expiration_date,
                    t.status as task.status
            FROM tasks t
            JOIN user_tasks ut on t.id = ut.task.id
            WHERE ut.user_id = ?""";

    private final String ASSIGN = """
            INSERT INTO users_tasks(task_id, user_id)
            VALUES(?, ?)""";

    private final String DELETE = """
            DELETE FROM tasks
            WHERE id = ?""";

    private final String UPDATE = """
            UPDATE tasks
            SET title = ?,
                description = ?,
                expiration_date = ?,
                status = ?
                WHERE id = ?""";

    private final String CREATE = """
            INSERT INTO tasks (title, description, expiration_date, status)
            VALUES(?,?,?,?)
            """;

    @Override
    public Optional<Task> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding user id");
        }
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_ID);
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                return TaskRowMapper.mapRows(rs);
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding all by user id");
        }
    }

    @Override
    public void assignToUserByID(Long taskId, Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(ASSIGN);
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while assigning to user");
        }
    }

    @Override
    public void update(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, task.getTitle());

            String description = task.getDescription();
            if (description != null) {
                statement.setString(2, description);
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (task.getExpirationDate() == null) {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            } else {
                statement.setNull(3, Types.TIMESTAMP);
            }

            statement.setString(4, task.getStatus().name());
            statement.setLong(5, task.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while update user");
        }
    }

    @Override
    public void create(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());

            String description = task.getDescription();
            if (description != null) {
                statement.setString(2, description);
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (task.getExpirationDate() == null) {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            } else {
                statement.setNull(3, Types.TIMESTAMP);
            }

            statement.setString(4, task.getStatus().name());

            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                task.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while create user");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(5, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while deleting user");
        }
    }
}
