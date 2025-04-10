package kr.amc.amis.admin.ui.dto.users;

import java.time.LocalDateTime;
import kr.amc.amis.common.utils.TimeCalculator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserTableResponseDto {

    @Getter
    private Long id;
    @Getter
    private String email;
    @Getter
    private String name;
    @Getter
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(this.createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(this.updatedAt);
    }

    public String getLastLoginAt() {
        return TimeCalculator.getFormattedDate(this.lastLoginAt);
    }
}
