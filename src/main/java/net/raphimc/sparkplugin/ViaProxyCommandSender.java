/*
 * This file is part of ViaProxySpark - https://github.com/ViaVersionAddons/ViaProxySpark
 * Copyright (C) 2024-2025 RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.raphimc.sparkplugin;

import com.viaversion.viaversion.api.command.ViaCommandSender;
import me.lucko.spark.common.command.sender.AbstractCommandSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.UUID;

public class ViaProxyCommandSender extends AbstractCommandSender<ViaCommandSender> {

    public ViaProxyCommandSender(final ViaCommandSender delegate) {
        super(delegate);
    }

    @Override
    public String getName() {
        return this.delegate.getName();
    }

    @Override
    public UUID getUniqueId() {
        return this.delegate.getUUID();
    }

    @Override
    public void sendMessage(final Component message) {
        this.delegate.sendMessage(LegacyComponentSerializer.legacySection().serialize(message));
    }

    @Override
    public boolean hasPermission(final String permission) {
        return this.delegate.hasPermission(permission);
    }

}
