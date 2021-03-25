package uz.pdp.lesson1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Department;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {

    @NotNull(message = "Name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak")
    private String phoneNumber;

    @NotNull(message = "Address bo'sh bo'lmasligi kerak")
    private Integer addressId;

    @NotNull(message = "Department bo'sh bo'lmasligi kerak")
    private Integer departmentId;
}
