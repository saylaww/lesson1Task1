package uz.pdp.lesson1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson1.entity.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    @NotNull(message = "CorpName bo'sh bo'lmasligi kerak")
    private String corpName;

    @NotNull(message = "DirectorName bo'sh bo'lmasligi kerak")
    private String directorName;

    @NotNull(message = "Address bo'sh bo'lmasligi kerak")
    private Integer addressId;
}
