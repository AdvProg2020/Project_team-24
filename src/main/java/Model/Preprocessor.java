package Model;

import Model.Models.Account;
import Model.Models.PersonalInformation;
import Model.Tools.FileHandler;
import Model.Tools.PackClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Preprocessor implements PackClass, FileHandler {

    private ModelUnit modelUnit;

    public Preprocessor(ModelUnit modelUnit) {
        this.modelUnit = modelUnit;
    }

    @Nullable
    private List<Pack> getPackList(File file) {
        try {
            return FileHandler.fileToStrings(file).stream()
                    .map(PackClass::unpack)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> getAccountList() {
        List<Pack> packList = getPackList(accountList_File);
        return packList.stream().map(pack -> {
            return
        }).collect(Collectors.toList());
    }

    public Account accountFromPack(Pack pack) {
        return new Account(
                (Integer) pack.getParam().get(0),
                String.valueOf(pack.getParam().get(1)),
                String.valueOf(pack.getParam().get(2)),
                (Account.StatusTag) pack.getParam().get(3),

        )
    }

    @Override
    public List<Object> getParametersForPack() {
        return null;
    }
}
