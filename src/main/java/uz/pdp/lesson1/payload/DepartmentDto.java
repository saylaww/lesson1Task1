package uz.pdp.lesson1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson1.entity.Company;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    @NotNull(message = "Name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "Company bo'sh bo'lmasligi kerak")
    private Integer companyId;
}
