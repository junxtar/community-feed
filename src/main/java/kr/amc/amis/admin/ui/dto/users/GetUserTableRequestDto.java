package kr.amc.amis.admin.ui.dto.users;

import kr.amc.amis.common.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserTableRequestDto extends Pageable {
    private String name;


}
