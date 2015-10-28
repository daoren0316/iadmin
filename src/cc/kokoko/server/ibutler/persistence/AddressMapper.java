package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Address;
import java.util.List;
import java.util.Map;

public abstract interface AddressMapper
{
  public abstract Address getAddressByAddressId(Long paramLong);

  public abstract List<Address> getAddressList(Long paramLong);

  public abstract void createAddress(Map<String, Object> paramMap);

  public abstract void deleteAddress(Long paramLong);

  public abstract void updateAddress(Address paramAddress);

  public abstract Address getUserAddressByHouseId(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.AddressMapper
 * JD-Core Version:    0.6.0
 */