//
//

package com.temp.jpa.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;
@Accessors(chain = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "task_deployment"
)
public class TaskDeployment {
    @Id
    @NotNull
    @Column(
        name = "task_deployment_id"
    )
    private String taskDeploymentId;
    @NotNull
    @Column(
        name = "platform_name"
    )
    private String platformName;
    @NotNull
    @Column(
        name = "task_definition_name"
    )
    private String taskDefinitionName;
    @CreatedDate
    @Column(
        name = "created_on"
    )
    private Instant createdOn;

}
